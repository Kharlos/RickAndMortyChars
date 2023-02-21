package com.cblanco.rickandmortychars.features

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.cblanco.rickandmortychars.NavGraphs
import com.cblanco.rickandmortychars.core.presentation.theme.RickAndMortyCharsTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.NavGraph

@NavGraph(
    default = true
)
annotation class DefaultNavGraph(
    val start:Boolean = false
)

@Composable
fun Navigation() {

    val navController = rememberNavController()

    RickAndMortyCharsTheme {
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.default
        )
    }

}