package com.emebesoft.movieProject.ui.states

import com.emebesoft.movieProject.domain.model.Character

sealed interface HomeUiState{
    object Loading : HomeUiState
    data class Success(val data: List<Character>) : HomeUiState
    data class Error(val Throwable: Throwable? = null): HomeUiState
}