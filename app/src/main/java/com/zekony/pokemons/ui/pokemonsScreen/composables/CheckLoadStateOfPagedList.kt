package com.zekony.pokemons.ui.pokemonsScreen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import com.zekony.pokemons.R

fun LazyListScope.checkLoadState(assetsListState: LoadState) {
    when (assetsListState) {
        is LoadState.Error -> {
            item {
                Text(
                    text = assetsListState.error.message
                        ?: stringResource(id = R.string.unknown_error),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        is LoadState.Loading -> {
            item {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = stringResource(R.string.loading),
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.primary
                    )

                    CircularProgressIndicator(color = Color.White)
                }
            }
        }

        else -> {}
    }
}