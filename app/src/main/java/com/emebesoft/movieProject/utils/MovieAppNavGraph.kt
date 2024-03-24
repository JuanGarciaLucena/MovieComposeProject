package com.emebesoft.movieProject.utils

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@NavGraph
annotation class MovieAppNavGraph(
    val start: Boolean = false
)