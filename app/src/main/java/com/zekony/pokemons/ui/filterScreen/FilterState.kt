package com.zekony.pokemons.ui.filterScreen

import androidx.annotation.Keep
import com.zekony.pokemons.data.remote.dto.filteredList.TypePokemonInfo
import com.zekony.pokemons.ui.util.DownloadState

@Keep
data class FilterState(
    val typePokemonInfo: TypePokemonInfo? = null,
    val downloadState: DownloadState = DownloadState.Loading
)