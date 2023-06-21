package com.emebesoft.movieProject.ui.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MyToolbar(
    title: String,
    navigationIcon: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar (
        title = { Text(text = title) },
        navigationIcon = navigationIcon ?: {},
        actions = actions
    )
}