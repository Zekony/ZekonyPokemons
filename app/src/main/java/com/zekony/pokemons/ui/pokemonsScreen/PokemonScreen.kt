package com.zekony.pokemons.ui.pokemonsScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.zekony.pokemons.R
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.ui.pokemonsScreen.composables.checkLoadState
import com.zekony.pokemons.ui.pokemonsScreen.composables.pokemonScreenList

@Composable
fun PokemonScreen(
    pagedList: LazyPagingItems<Pokemon>,
    onEvent: (PokemonEvent) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = stringResource(R.string.pokemons),
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            if (pagedList.loadState.refresh is LoadState.Error) {
                Text(
                    text = (pagedList.loadState.refresh as LoadState.Error).error.message
                        ?: stringResource(id = R.string.unknown_error),
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            } else {
                LazyColumn(
                    state = lazyListState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    modifier = Modifier.fillMaxSize()
                ) {
                    pokemonScreenList(pagedList, onEvent)
                    checkLoadState(pagedList.loadState.refresh)
                    checkLoadState(pagedList.loadState.append)
                }
            }
        }
    }
}

