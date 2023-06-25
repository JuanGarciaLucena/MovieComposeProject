package com.emebesoft.movieProject.ui.common

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.emebesoft.movieProject.ui.screens.Detail
import com.emebesoft.movieProject.ui.screens.Home
import com.emebesoft.movieProject.ui.viewmodel.RickMortyCharacterViewModel


class Navigation {

    @Composable
    fun MyAppNavHost() {
        val navController = rememberNavController()
        val viewModel: RickMortyCharacterViewModel = viewModel()
        NavHost(navController = navController, startDestination = "Home") {
            composable("Home") { Home(navController).HomeMain(viewModel) }
            composable(
                route = "Detail/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.StringType }))
            { backStackNavEntry ->
                Detail(navController = navController, backStackNavEntry.arguments?.getString("characterId")!!).DetailMain(viewModel)
            }
        }
    }
}