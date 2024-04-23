package com.zekony.pokemons.data.remote.dto.abilityInfo

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EffectEntry(
    val effect: String,
    val language: Language,
    @SerializedName("short_effect")
    val shortEffect: String
)

