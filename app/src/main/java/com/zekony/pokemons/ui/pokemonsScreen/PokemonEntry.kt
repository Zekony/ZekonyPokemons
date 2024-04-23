package com.zekony.pokemons.ui.pokemonsScreen

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.zekony.pokemons.ui.destinations.InfoEntryDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

@RootNavGraph(start = true)
@Destination
@Composable
fun PokemonEntry(
    navigator: DestinationsNavigator
) {
    val viewModel: PokemonViewModel = koinViewModel()
    val pagedList = viewModel.getPagingPokemonsList().collectAsLazyPagingItems()

    PokemonScreen(pagedList = pagedList, onEvent = viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is PokemonSideEffect.NavigateToInfoScreen -> {
                navigator.navigate(InfoEntryDestination(sideEffect.name))
            }
        }
    }
}
