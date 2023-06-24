package com.emebesoft.movieProject.data.repository

import com.emebesoft.movieProject.data.database.dao.RickMortyDao
import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import com.emebesoft.movieProject.data.network.RickMortyDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val rickMortyDataSource: RickMortyDataSource,
    private val rickMortyDao: RickMortyDao
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<CharacterEntity>> = flow {

        if (rickMortyDao.getCharactersFromDatabase().isEmpty()) {
            rickMortyDao.saveCharacters(rickMortyDataSource.fetchCharactersFromApi().map { characterItem ->
                CharacterEntity(
                    id = characterItem.id,
                    name = characterItem.name,
                    status = characterItem.status,
                    species = characterItem.species,
                    type = characterItem.type,
                    gender = characterItem.gender,
                    image = characterItem.image,
                    episode = characterItem.episode,
                    url = characterItem.url,
                    created = characterItem.created,
                    locationName = characterItem.location.name,
                    locationUrl = characterItem.location.url,
                    originName = characterItem.origin.name,
                    originUrl = characterItem.origin.url
                )
            })
        }
        emit(rickMortyDao.getCharactersFromDatabase())
    }

    override suspend fun getCharacter(characterId: String): Flow<CharacterEntity> = flow {
        emit(rickMortyDao.getCharacterById(characterId))
    }
}