package com.zekony.pokemons.data.remote.dto.filteredList

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import androidx.annotation.Keep

@Keep
@Serializable
data class DamageRelations(
    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<DoubleDamageFrom>,
    @SerializedName("double_damage_to")
    val doubleDamageTo: List<DoubleDamageTo>,
    @SerializedName("half_damage_from")
    val halfDamageFrom: List<HalfDamageFrom>,
    @SerializedName("half_damage_to")
    val halfDamageTo: List<HalfDamageTo>,
    @SerializedName("no_damage_from")
    val noDamageFrom: List<NoDamageFrom>?,
    @SerializedName("no_damage_to")
    val noDamageTo: List<NoDamageTo>?
)
