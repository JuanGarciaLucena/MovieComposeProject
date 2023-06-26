package com.emebesoft.movieProject.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.emebesoft.baseProject.R

@Composable
fun MyToolbar(
    title: String,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar (
        title = { Text(text = title, color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(id = R.color.blue)),
        navigationIcon = navigationIcon ?: {},
        actions = actions
    )
}