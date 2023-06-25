package com.emebesoft.movieProject.ui.states

import com.emebesoft.movieProject.domain.model.Character

sealed interface DetailUiState{

    object Loading : DetailUiState
    data class Success(val data: Character) : DetailUiState
    data class Error(val Throwable: Throwable? = null): DetailUiState
}