package com.emebesoft.movieProject.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emebesoft.movieProject.ui.common.MyToolbar

class Detail(private val navController: NavController, private val characterId: String) {

    @Composable
    fun DetailMain(){
        Scaffold(
            topBar = {
                MyToolbar(
                    title = "Detail",
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Navegación hacia atrás")
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* Acción adicional */ }) {
                            Icon(Icons.Default.Add, contentDescription = "Añadir")
                        }
                    }
                )
            }
        ) {
            it.calculateBottomPadding()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 56.dp)
            ){
                Text(text = "Que pasa tronco")
            }
        }
    }
}