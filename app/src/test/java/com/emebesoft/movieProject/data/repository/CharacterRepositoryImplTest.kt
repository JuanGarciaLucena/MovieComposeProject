package com.emebesoft.movieProject.data.repository


import com.emebesoft.movieProject.data.database.dao.RickMortyDao
import com.emebesoft.movieProject.data.database.entity.RickMortyCharacterEntity
import com.emebesoft.movieProject.data.network.RickMortyDataSource
import com.emebesoft.movieProject.domain.model.toCharacter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.single
import com.emebesoft.movieProject.data.model.CharacterModelResult
import com.emebesoft.movieProject.domain.model.Character
import com.emebesoft.movieProject.data.model.Location
import com.emebesoft.movieProject.data.model.Origin
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CharacterRepositoryImplTest {

    private val characterList = listOf(
        CharacterModelResult(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "Mad scientist",
            gender = "Male",
            origin = Origin("Earth", "https://example.com/earth"),
            location = Location("Citadel of Ricks", "https://example.com/citadel"),
            image = "https://example.com/rick_image.jpg",
            episode = listOf("https://example.com/episode1", "https://example.com/episode2"),
            url = "https://example.com/rick",
            created = "2021-09-01T10:00:00Z"
        ),
        CharacterModelResult(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            origin = Origin("Earth", "https://example.com/earth"),
            location = Location("Earth", "https://example.com/earth"),
            image = "https://example.com/morty_image.jpg",
            episode = listOf("https://example.com/episode1", "https://example.com/episode3"),
            url = "https://example.com/morty",
            created = "2021-09-02T08:30:00Z"
        ),
        CharacterModelResult(
            id = 3,
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Female",
            origin = Origin("Earth", "https://example.com/earth"),
            location = Location("Earth", "https://example.com/earth"),
            image = "https://example.com/summer_image.jpg",
            episode = listOf("https://example.com/episode2", "https://example.com/episode3"),
            url = "https://example.com/summer",
            created = "2021-09-03T15:45:00Z"
        )
    )

    private val characterEntityList = listOf(
        RickMortyCharacterEntity(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            type = "Mad scientist",
            gender = "Male",
            image = "https://example.com/rick_image.jpg",
            episode = listOf("https://example.com/episode1", "https://example.com/episode2"),
            url = "https://example.com/rick",
            created = "2021-09-01T10:00:00Z",
            locationName = "Citadel of Ricks",
            locationUrl = "https://example.com/citadel",
            originName = "Earth",
            originUrl = "https://example.com/earth"
        ),
        RickMortyCharacterEntity(
            id = 2,
            name = "Morty Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Male",
            image = "https://example.com/morty_image.jpg",
            episode = listOf("https://example.com/episode1", "https://example.com/episode3"),
            url = "https://example.com/morty",
            created = "2021-09-02T08:30:00Z",
            locationName = "Earth",
            locationUrl = "https://example.com/earth",
            originName = "Earth",
            originUrl = "https://example.com/earth"
        ),
        RickMortyCharacterEntity(
            id = 3,
            name = "Summer Smith",
            status = "Alive",
            species = "Human",
            type = "",
            gender = "Female",
            image = "https://example.com/summer_image.jpg",
            episode = listOf("https://example.com/episode2", "https://example.com/episode3"),
            url = "https://example.com/summer",
            created = "2021-09-03T15:45:00Z",
            locationName = "Earth",
            locationUrl = "https://example.com/earth",
            originName = "Earth",
            originUrl = "https://example.com/earth"
        )
    )

    private val characterEntity = RickMortyCharacterEntity(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "Mad scientist",
        gender = "Male",
        image = "https://example.com/rick_image.jpg",
        episode = listOf("https://example.com/episode1", "https://example.com/episode2"),
        url = "https://example.com/rick",
        created = "2021-09-01T10:00:00Z",
        locationName = "Citadel of Ricks",
        locationUrl = "https://example.com/citadel",
        originName = "Earth",
        originUrl = "https://example.com/earth"
    )

    // Mocks
    @Mock
    private lateinit var mockRickMortyDataSource: RickMortyDataSource

    @Mock
    private lateinit var mockRickMortyDao: RickMortyDao

    // System under test
    private lateinit var characterRepository: CharacterRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        characterRepository = CharacterRepositoryImpl(mockRickMortyDataSource, mockRickMortyDao)
    }

    @Test
    fun `getCharacters should return characters from database when available`() = runTest {
        // Mock the behavior of the DAO to return characters from the database

        `when`(mockRickMortyDao.getCharactersFromDatabase()).thenReturn(characterEntityList)

        // Call the method under test
        var result: List<Character> = emptyList()

        characterRepository.getCharacters().collect {
            result = it
        }

        // Verify the result
        assertEquals(characterEntityList.map { it.toCharacter() }, result)
    }

    @Test
    fun `getCharacters should fetch characters from API and save to database when empty`() = runTest {
        // Mock the behavior of the DAO to return an empty list of characters
        `when`(mockRickMortyDao.getCharactersFromDatabase()).thenReturn(emptyList())

        // Mock the behavior of the data source to return characters from the API

        `when`(mockRickMortyDataSource.fetchCharactersFromApi()).thenReturn(characterList)

        // Call the method under test
        characterRepository.getCharacters().collect()

        // Verify that the characters are saved to the database
        val expectedEntities = characterList.map { character ->
            RickMortyCharacterEntity(
                id = character.id,
                name = character.name,
                status = character.status,
                species = character.species,
                type = character.type,
                gender = character.gender,
                image = character.image,
                episode = character.episode,
                url = character.url,
                created = character.created,
                locationName = character.location.name,
                locationUrl = character.location.url,
                originName = character.origin.name,
                originUrl = character.origin.url
            )
        }
        verify(mockRickMortyDao).saveCharacters(expectedEntities)
    }

    @Test
    fun `getCharacter should return character from database`() = runTest {
        // Mock the behavior of the DAO to return a character from the database
        val characterId = "123"

        `when`(mockRickMortyDao.getCharacterById(characterId)).thenReturn(characterEntity)

        // Call the method under test
        val result: Character = characterRepository.getCharacter(characterId).single()

        // Verify the result
        assertEquals(characterEntity.toCharacter(), result)
    }
}