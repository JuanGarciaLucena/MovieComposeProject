package com.emebesoft.movieProject.ui.screens.home

import androidx.compose.runtime.Composable
import com.emebesoft.movieProject.ui.common.ErrorMain
import com.emebesoft.movieProject.ui.common.LoadingScreen
import com.emebesoft.movieProject.ui.states.HomeUiState

@Composable
fun HomeUiStateManager(homeUiState: HomeUiState) {
    when (homeUiState) {
        is HomeUiState.Loading -> LoadingScreen()
        is HomeUiState.Success -> CharacterList(characterList = homeUiState.data)
        is HomeUiState.Error -> ErrorMain()
    }
}