package com.zekony.pokemons.ui.filterScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.zekony.pokemons.R
import com.zekony.pokemons.data.remote.dto.filteredList.PokemonX
import com.zekony.pokemons.ui.filterScreen.composables.DoubleDamageFromColumn
import com.zekony.pokemons.ui.filterScreen.composables.DoubleDamageToColumn
import com.zekony.pokemons.ui.filterScreen.composables.HalfDamageFromColumn
import com.zekony.pokemons.ui.filterScreen.composables.HalfDamageToColumn
import com.zekony.pokemons.ui.filterScreen.composables.NoDamageFromColumn
import com.zekony.pokemons.ui.filterScreen.composables.NoDamageToColumn
import com.zekony.pokemons.ui.filterScreen.composables.PagedColumnItem
import com.zekony.pokemons.ui.pokemonsScreen.composables.checkLoadState
import com.zekony.pokemons.ui.util.DownloadState

@Composable
fun FilterScreen(
    pagedList: LazyPagingItems<PokemonX>,
    state: FilterState,
    onEvent: (FilterEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when (state.downloadState) {
            DownloadState.Error -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = stringResource(id = R.string.error))
            }

            DownloadState.Loading ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.White)
                }

            DownloadState.Success -> FilterScreenContent(pagedList, state, onEvent)
        }
    }
}

@Composable
fun FilterScreenContent(
    pagedList: LazyPagingItems<PokemonX>,
    state: FilterState,
    onEvent: (FilterEvent) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (state.typePokemonInfo != null) {
            val pokemonType = state.typePokemonInfo
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = pokemonType.name.replaceFirstChar { it.uppercase() }, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(12.dp))
                DoubleDamageToColumn(pokemonType)
                DoubleDamageFromColumn(pokemonType)
                HalfDamageToColumn(pokemonType)
                HalfDamageFromColumn(pokemonType)
                NoDamageToColumn(pokemonType)
                NoDamageFromColumn(pokemonType)
            }
            items(
                count = pagedList.itemCount
            ) { index ->
                pagedList[index]?.let {
                    PagedColumnItem(it, onEvent)
                }
            }
            checkLoadState(pagedList.loadState.refresh)
            checkLoadState(pagedList.loadState.append)
        }
    }
}



