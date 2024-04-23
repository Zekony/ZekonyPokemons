package com.zekony.pokemons.ui.pokemonsScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.ui.pokemonsScreen.PokemonEvent


fun LazyListScope.pokemonScreenList(
    pagedList: LazyPagingItems<Pokemon>,
    onEvent: (PokemonEvent) -> Unit
) {
    items(
        count = pagedList.itemCount
    ) { index ->
        val item = pagedList[index]
        if (item != null) {
            Card(modifier = Modifier
                .fillMaxWidth(0.8f)
                .clickable { onEvent(PokemonEvent.NavigateToInfoScreen(item.name)) }) {
                Text(
                    text = item.name.replaceFirstChar { it.uppercase() },
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}