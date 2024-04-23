package com.zekony.pokemons.ui.navigation

import androidx.compose.runtime.Composable
import com.zekony.pokemons.ui.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun Navigation() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}