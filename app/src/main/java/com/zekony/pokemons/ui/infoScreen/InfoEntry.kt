package com.zekony.pokemons.ui.infoScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.zekony.pokemons.ui.destinations.FilterEntryDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination
@Composable
fun InfoEntry(
    name: String,
    navigator: DestinationsNavigator
) {
    val viewModel: InfoViewModel = koinViewModel()
    val state = viewModel.collectAsState().value
    LaunchedEffect(Unit) {
        if (state.pokemonInfo == null) viewModel.dispatch(InfoEvent.FetchPokemonInfo(name))
    }
    InfoScreen(state = state, onEvent = viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is InfoSideEffect.NavigateToFilterScreen -> navigator.navigate(
                FilterEntryDestination(sideEffect.type)
            )
        }
    }
}
