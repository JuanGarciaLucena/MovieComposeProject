package com.emebesoft.movieProject.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import com.emebesoft.movieProject.ui.common.MyToolbar
import com.emebesoft.movieProject.ui.common.UiState
import com.emebesoft.movieProject.ui.viewmodel.CharacterViewModel
import com.google.gson.Gson

class Home(private val navController: NavController) {

    @Composable
    fun HomeMain(viewModel: CharacterViewModel) {

        viewModel.getAllCharacters()
        val uiState by viewModel.characterFlow.collectAsStateWithLifecycle()

        Scaffold(topBar = { MyToolbar(title = "Home") }) {
            it.calculateBottomPadding()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
            ) {
                HomeUiStateManager(uiState)
            }
        }
    }

    @Composable
    fun HomeUiStateManager(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> Log.i("LOADING", "LOADING")
            is UiState.Success -> FillGrid(characterList = uiState.data)
            is UiState.Error -> uiState.Throwable
        }
    }

    @Composable
    fun FillGrid(characterList: List<CharacterEntity>) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp)
        ) {
            for (character in characterList) {
                item { gridItemView(character = character) }
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun gridItemView(character: CharacterEntity) {

        Box(modifier = Modifier.padding(10.dp)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("Detail/${character.id}") }
            ) {
                GlideImage(
                    model = character.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                )
                Text(
                    text = character.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}