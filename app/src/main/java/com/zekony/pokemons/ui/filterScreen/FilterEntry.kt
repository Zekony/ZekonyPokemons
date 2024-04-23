package com.zekony.pokemons.ui.filterScreen

import androidx.compose.runtime.Composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.zekony.pokemons.ui.destinations.InfoEntryDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Destination
@Composable
fun FilterEntry(
    type: String,
    navigator: DestinationsNavigator
) {
    val viewModel: FilterViewModel = koinViewModel(parameters = { parametersOf(type) })
    val state = viewModel.collectAsState().value
    val pagedList = viewModel.getPagingFilteredPokemonsList().collectAsLazyPagingItems()

    FilterScreen(pagedList = pagedList, state = state, onEvent = viewModel::dispatch)

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is FilterSideEffect.NavigateToInfoScreen -> navigator.navigate(InfoEntryDestination(sideEffect.name))
        }
    }
}
