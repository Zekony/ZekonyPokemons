package com.zekony.pokemons.data.remote.dto.abilityInfo

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AbilityInfo(
    @SerializedName("effect_entries")
    val effectEntries: List<EffectEntry>,
    val name: String
)