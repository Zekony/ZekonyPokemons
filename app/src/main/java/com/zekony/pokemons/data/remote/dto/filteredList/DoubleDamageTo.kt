package com.zekony.pokemons.data.remote.dto.filteredList
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DoubleDamageTo(
    val name: String,
    val url: String
)