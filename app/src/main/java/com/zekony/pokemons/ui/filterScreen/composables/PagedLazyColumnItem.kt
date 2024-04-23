package com.zekony.pokemons.ui.filterScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zekony.pokemons.data.remote.dto.filteredList.PokemonX
import com.zekony.pokemons.ui.filterScreen.FilterEvent


@Composable
fun PagedColumnItem(pokemon: PokemonX, onEvent: (FilterEvent) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth(0.8f)
        .clickable { onEvent(FilterEvent.NavigateToInfoScreen(pokemon.name)) }) {
        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }

}