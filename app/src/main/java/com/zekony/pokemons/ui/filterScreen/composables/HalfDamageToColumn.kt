package com.zekony.pokemons.ui.filterScreen.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zekony.pokemons.data.remote.dto.filteredList.TypePokemonInfo
import com.zekony.pokemons.ui.filterScreen.util.separateWord

@Composable
fun HalfDamageToColumn(pokemonType: TypePokemonInfo) {
    if (pokemonType.damageRelations.halfDamageTo.isNotEmpty()) {
        Text(text = pokemonType.damageRelations.halfDamageTo.first().javaClass.simpleName.separateWord()
            .replaceFirstChar { it.uppercase() },
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold
        )
        pokemonType.damageRelations.halfDamageTo.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = pokemonType.name, fontSize = 24.sp)
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = it.name, fontSize = 24.sp)
            }
        }
    }
}