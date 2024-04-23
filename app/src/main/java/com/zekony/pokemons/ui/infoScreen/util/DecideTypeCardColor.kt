package com.zekony.pokemons.ui.infoScreen.util

import androidx.compose.ui.graphics.Color
import com.zekony.pokemons.theme.earthBrown
import com.zekony.pokemons.theme.skyBlue


fun decideTypeCardColor(type: String): Color {
    return when (type) {
        "fighting" -> Color.Red
        "flying" -> skyBlue
        "poison" -> Color.Green
        "ground" -> earthBrown
        "rock" -> Color.Gray
        "bug" -> earthBrown
        "ghost" -> Color.LightGray
        "steel" -> Color.Gray
        "fire" -> Color.Red
        "water" -> skyBlue
        "grass" -> Color.Green
        "electric" -> Color.Yellow
        "psychic" -> Color.Magenta
        "ice" -> skyBlue
        "dragon" -> Color.Red
        "dark" -> Color.DarkGray
        "fairy" -> Color.Yellow
        "shadow" -> Color.DarkGray
        else -> Color.LightGray
    }
}