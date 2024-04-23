package com.zekony.pokemons.data.remote.dto

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NamedApiResourceList(
    @SerialName("results")
    val results: List<NamedApiResource>
)

@Keep
@Serializable
data class NamedApiResource(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)
