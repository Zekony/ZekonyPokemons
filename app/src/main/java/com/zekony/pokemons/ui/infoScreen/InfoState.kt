package com.zekony.pokemons.ui.infoScreen

import androidx.annotation.Keep
import com.zekony.pokemons.data.remote.dto.PokemonInfo
import com.zekony.pokemons.data.remote.dto.abilityInfo.AbilityInfo
import com.zekony.pokemons.ui.util.DownloadState

@Keep
data class InfoState(
    val pokemonInfo: PokemonInfo? = null,
    val abilityInfo: AbilityInfo? = null,
    val pokemonInfoDownloadState: DownloadState = DownloadState.Loading,
    val abilityInfoDownloadState: DownloadState = DownloadState.Loading,
    val isAbilityInfoDialogOpen: Boolean = false
)