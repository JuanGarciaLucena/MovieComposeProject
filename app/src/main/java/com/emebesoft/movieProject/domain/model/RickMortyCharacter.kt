package com.emebesoft.movieProject.domain.model

import com.emebesoft.movieProject.data.database.entity.RickMortyCharacterEntity

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
    val locationName: String,
    val locationUrl: String,
    val originName: String,
    val originUrl: String
)

fun RickMortyCharacterEntity.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        episode = episode,
        url = url,
        created = created,
        locationName = locationName,
        locationUrl = locationUrl,
        originName = originName,
        originUrl = originUrl
    )
}

fun Character.toCharacterEntity(): RickMortyCharacterEntity {
    return RickMortyCharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        episode = episode,
        url = url,
        created = created,
        locationName = locationName,
        locationUrl = locationUrl,
        originName = originName,
        originUrl = originUrl
    )
}