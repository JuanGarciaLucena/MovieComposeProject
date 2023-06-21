package com.emebesoft.movieProject.ui.common

import com.emebesoft.movieProject.data.database.entity.CharacterEntity

sealed interface UiState{
    object Loading : UiState

    data class Success(
        val data: List<CharacterEntity>
    ) : UiState

    data class Error(
        val Throwable: Throwable? = null
    ): UiState
}