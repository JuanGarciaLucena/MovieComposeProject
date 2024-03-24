package com.emebesoft.movieProject.ui.screens.detail

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.emebesoft.movieProject.ui.common.LoadingScreen
import com.emebesoft.movieProject.ui.states.DetailUiState

@Composable
fun HomeUiStateManager(detailUiState: DetailUiState) {
    when (detailUiState) {
        is DetailUiState.Loading -> LoadingScreen()
        is DetailUiState.Success -> CharacterHeader(character = detailUiState.data)
        is DetailUiState.Error -> Toast.makeText(
            LocalContext.current,
            detailUiState.Throwable!!.message,
            Toast.LENGTH_LONG
        ).show()
    }
}