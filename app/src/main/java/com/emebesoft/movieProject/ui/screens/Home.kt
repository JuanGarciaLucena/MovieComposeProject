package com.emebesoft.movieProject.ui.screens

import android.graphics.drawable.VectorDrawable
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.emebesoft.baseProject.R
import com.emebesoft.movieProject.data.database.entity.CharacterEntity
import com.emebesoft.movieProject.ui.common.MyToolbar
import com.emebesoft.movieProject.ui.common.HomeUiState
import com.emebesoft.movieProject.ui.common.LoadingUi
import com.emebesoft.movieProject.ui.viewmodel.CharacterViewModel

class Home(private val navController: NavController) {

    @Composable
    fun HomeMain(viewModel: CharacterViewModel) {

        viewModel.getAllCharacters()
        val uiState by viewModel.characterFlow.collectAsStateWithLifecycle()
        Scaffold(topBar = { MyToolbar(title = "Rick & Morty") }) {
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
    fun HomeUiStateManager(homeUiState: HomeUiState) {
        when (homeUiState) {
            is HomeUiState.Loading -> LoadingUi().LoadingScreen()
            is HomeUiState.Success -> FillGrid(characterList = homeUiState.data)
            is HomeUiState.Error -> Error().ErrorMain()
        }
    }

    @Composable
    fun FillGrid(characterList: List<CharacterEntity>) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            for (character in characterList) {
                item { gridItemView(character = character) }
            }
        }
    }

    @Composable
    fun gridItemView(character: CharacterEntity) {

        Box(modifier = Modifier.padding(10.dp)) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.blue)
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("Detail/${character.id}") }
            ) {

                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.ic_placeholder)
                )

                /*GlideImage(
                    model = character.image,
                    contentScale = ContentScale.Crop,
                    contentDescription = "",
                    error = {VectorDrawable.(id = R.drawable.ic_placeholder)},
                    modifier = Modifier
                        .fillMaxSize(),
                )*/

                Text(
                    text = character.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    color = Color.White
                )
            }
        }
    }
}