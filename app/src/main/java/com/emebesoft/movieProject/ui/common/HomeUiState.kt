package com.emebesoft.movieProject.ui.common

import com.emebesoft.movieProject.data.database.entity.CharacterEntity

sealed interface HomeUiState{
    object Loading : HomeUiState

    data class Success(
        val data: List<CharacterEntity>
    ) : HomeUiState

    data class Error(
        val Throwable: Throwable? = null
    ): HomeUiState
}