package com.emebesoft.movieProject.data.repository

import com.emebesoft.movieProject.data.database.dao.RickMortyDao
import com.emebesoft.movieProject.data.database.entity.RickMortyCharacterEntity
import com.emebesoft.movieProject.data.network.RickMortyDataSource
import com.emebesoft.movieProject.domain.model.Character
import com.emebesoft.movieProject.domain.model.toCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val rickMortyDataSource: RickMortyDataSource,
    private val rickMortyDao: RickMortyDao
) : CharacterRepository {

    override suspend fun getCharacters(): Flow<List<Character>> = flow {

        if (rickMortyDao.getCharactersFromDatabase().isEmpty()) {
            rickMortyDao.saveCharacters(rickMortyDataSource.fetchCharactersFromApi().map { characterItem ->
                RickMortyCharacterEntity(
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
        emit(rickMortyDao.getCharactersFromDatabase().map { characterEntity ->  characterEntity.toCharacter()})
    }

    override suspend fun getCharacter(characterId: String): Flow<Character> = flow {
        emit(rickMortyDao.getCharacterById(characterId).toCharacter())
    }
}