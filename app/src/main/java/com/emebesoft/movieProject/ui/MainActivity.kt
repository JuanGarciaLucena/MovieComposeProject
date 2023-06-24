package com.emebesoft.movieProject.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.emebesoft.movieProject.ui.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.emebesoft.movieProject.ui.common.Navigation

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<CharacterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigation().MyAppNavHost()
        }
    }
}