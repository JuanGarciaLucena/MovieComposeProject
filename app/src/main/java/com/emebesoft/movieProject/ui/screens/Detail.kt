package com.emebesoft.movieProject.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.emebesoft.baseProject.R
import com.emebesoft.movieProject.domain.model.Character
import com.emebesoft.movieProject.ui.states.DetailUiState
import com.emebesoft.movieProject.ui.common.LoadingUi
import com.emebesoft.movieProject.ui.common.MyToolbar
import com.emebesoft.movieProject.ui.viewmodel.RickMortyCharacterViewModel
import com.emebesoft.movieProject.utils.Constants
import com.emebesoft.movieProject.utils.DateUtils
import com.emebesoft.movieProject.utils.DetailNavigationArgs
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private lateinit var appNavigator: DestinationsNavigator

@Destination(navArgsDelegate = DetailNavigationArgs::class)
@Composable
fun DetailMain(
    navigator: DestinationsNavigator,
    viewModel: RickMortyCharacterViewModel = hiltViewModel(),
    characterArgs: DetailNavigationArgs
) {
    appNavigator = navigator
    viewModel.getCharacter(characterArgs.characterId.toString())
    val uiState by viewModel.detailFlow.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyToolbar(
                title = "",
                navigationIcon = {
                    IconButton(onClick = { navigator.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.content_description_back_nav),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        it.calculateBottomPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 60.dp)
        ) {
            HomeUiStateManager(uiState)
        }
    }
}

@Composable
fun HomeUiStateManager(detailUiState: DetailUiState) {
    when (detailUiState) {
        is DetailUiState.Loading -> LoadingUi().LoadingScreen()
        is DetailUiState.Success -> CharacterHeader(character = detailUiState.data)
        is DetailUiState.Error -> Toast.makeText(
            LocalContext.current,
            detailUiState.Throwable!!.message,
            Toast.LENGTH_LONG
        ).show()
    }
}

@Composable
fun CharacterHeader(character: Character) {
    MaterialTheme {
        Box(
            Modifier.padding(top = 50.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(175.dp),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.ic_placeholder)
                )

                Text(
                    text = character.name,
                    color = Color.Black, fontSize = 30.sp
                )

                Text(
                    text = "${character.gender}, ${character.species}",
                    color = Color.Black
                )

                Divider(
                    color = colorResource(id = R.color.blue),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Box {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (character.status == Constants.ALIVE) {
                                Image(
                                    modifier = Modifier.size(60.dp),
                                    painter = painterResource(id = R.drawable.ic_alive),
                                    contentDescription = Constants.ALIVE
                                )
                            } else {
                                Image(
                                    modifier = Modifier.size(60.dp),
                                    painter = painterResource(id = R.drawable.ic_dead),
                                    contentDescription = Constants.ALIVE
                                )
                            }

                            Text(

                                text = character.status,
                                color = Color.Black
                            )
                        }
                    }

                    Box {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {

                            Image(
                                modifier = Modifier.size(60.dp),
                                painter = painterResource(id = R.drawable.ic_planet),
                                contentDescription = Constants.ALIVE
                            )

                            Text(
                                modifier = Modifier.wrapContentWidth(),
                                text = character.originName.replace(" (", "\n("),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Divider(
                    color = colorResource(id = R.color.blue),
                    modifier = Modifier.padding(top = 20.dp)
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blue_light)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, top = 20.dp),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(Modifier.padding(20.dp)) {
                            Text(
                                text = stringResource(id = R.string.detail_character_info_label),
                                fontSize = 25.sp,
                                modifier = Modifier.padding(bottom = 10.dp),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = stringResource(
                                    R.string.detail_num_episodes,
                                    character.episode.size
                                )
                            )
                            Text(
                                text = stringResource(
                                    R.string.detail_creation_date,
                                    DateUtils.dateConverter(character.created)
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}