package com.zekony.pokemons.data.remote.dto

import androidx.annotation.Keep
import com.zekony.pokemons.data.remote.dto.pokemonInfo.Ability
import com.zekony.pokemons.data.remote.dto.pokemonInfo.Form
import com.zekony.pokemons.data.remote.dto.pokemonInfo.Move
import com.zekony.pokemons.data.remote.dto.pokemonInfo.Sprites
import com.zekony.pokemons.data.remote.dto.pokemonInfo.Type
import kotlinx.serialization.SerialName

@Keep
data class PokemonInfo(
    val abilities: List<Ability>,
    @SerialName("base_experience")
    val baseExperience: Int,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int
)