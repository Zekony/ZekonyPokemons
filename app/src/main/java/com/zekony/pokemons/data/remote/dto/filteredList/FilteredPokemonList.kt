package com.zekony.pokemons.data.remote.dto.filteredList

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class FilteredPokemonList(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelations,
    val id: Int,
    val name: String,
    val pokemon: List<Pokemon>
)

data class TypePokemonInfo(
    val damageRelations: DamageRelations,
    val name: String,
)

fun FilteredPokemonList.toTypePokemonInfo(): TypePokemonInfo =
    TypePokemonInfo(
        damageRelations, name
    )