package com.emebesoft.movieProject.ui.common

import com.emebesoft.movieProject.data.database.entity.CharacterEntity

sealed interface DetailUiState{

    object Loading : DetailUiState

    data class Success(
        val data: CharacterEntity
    ) : DetailUiState

    data class Error(
        val Throwable: Throwable? = null
    ): DetailUiState
}