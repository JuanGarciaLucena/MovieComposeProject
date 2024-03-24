package com.emebesoft.movieProject.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.emebesoft.baseProject.R
import com.emebesoft.movieProject.ui.common.AppToolbar
import com.emebesoft.movieProject.ui.viewmodel.RickMortyCharacterViewModel
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.emebesoft.movieProject.domain.model.Character
import com.emebesoft.movieProject.ui.screens.destinations.DetailMainDestination
import com.emebesoft.movieProject.utils.MovieAppNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


private lateinit var appNavigator: DestinationsNavigator

@MovieAppNavGraph(start = true)
@Destination
@Composable
fun HomeMain(
    navigator: DestinationsNavigator,
    viewModel: RickMortyCharacterViewModel = hiltViewModel()
) {
    appNavigator = navigator
    val uiState by viewModel.characterFlow.collectAsStateWithLifecycle()
    HomeUiStateManager(uiState)
}

@Composable
fun CharacterList(characterList: List<Character>) {

    var queryText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Scaffold(topBar = { AppToolbar(title = stringResource(id = R.string.app_name)) }) {
        it.calculateBottomPadding()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp)
        ) {

            SearchBar(
                modifier = Modifier.align(Alignment.TopCenter),
                query = queryText,
                onQueryChange = { queryText = it },
                onSearch = { active = false },
                active = true,
                onActiveChange = { isActive ->
                    active = isActive
                },
                placeholder = { Text(stringResource(id = R.string.home_search_hint)) },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search, contentDescription = stringResource(
                            id = R.string.content_description_search_character
                        )
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable { queryText = "" })
                }
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(200.dp),
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {

                    if (queryText.isNotEmpty()) {
                        items(characterList.filter { item ->
                            item.name.lowercase().contains(queryText.lowercase())
                        }) { character ->
                            GridItemView(character = character)
                        }
                    } else {
                        items(characterList) { character ->
                            GridItemView(character = character)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun GridItemView(character: Character) {

    Box(modifier = Modifier.padding(10.dp)) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.blue)
            ),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    appNavigator.navigate(DetailMainDestination(character.id))
                }
        ) {

            AsyncImage(
                model = character.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.ic_placeholder)
            )

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