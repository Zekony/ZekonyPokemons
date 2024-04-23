package com.zekony.pokemons.ui.infoScreen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zekony.pokemons.R
import com.zekony.pokemons.data.remote.dto.pokemonInfo.Ability
import com.zekony.pokemons.ui.infoScreen.InfoEvent

@Composable
fun AbilitiesColumn(abilities: List<Ability>, onEvent: (InfoEvent) -> Unit) {
    Text(text = stringResource(R.string.abilities), fontSize = 24.sp)
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(abilities) { ability ->
            Card(
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .clickable {
                        onEvent(InfoEvent.FetchAbilityInfo(ability.ability.name))
                        onEvent(InfoEvent.OpenAbilityInfoDialog(true))
                    }
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = ability.ability.name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}