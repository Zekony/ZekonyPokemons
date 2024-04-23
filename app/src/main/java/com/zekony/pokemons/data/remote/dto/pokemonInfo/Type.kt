package com.zekony.pokemons.data.remote.dto.pokemonInfo
import androidx.annotation.Keep

@Keep
data class Type(
    val slot: Int,
    val type: TypeX
)