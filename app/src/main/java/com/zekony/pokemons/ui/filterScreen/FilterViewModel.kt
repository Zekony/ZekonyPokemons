package com.zekony.pokemons.ui.filterScreen

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zekony.pokemons.data.remote.Resource
import com.zekony.pokemons.data.remote.dto.filteredList.PokemonX
import com.zekony.pokemons.domain.PokemonApiRepository
import com.zekony.pokemons.ui.MviViewModel
import com.zekony.pokemons.ui.util.DownloadState
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce


class FilterViewModel(
    private val type: String,
    private val apiRepository: PokemonApiRepository,
) : MviViewModel<FilterState, FilterSideEffect, FilterEvent>(
    initialState = FilterState()
) {
    fun getPagingFilteredPokemonsList(): Flow<PagingData<PokemonX>> =
        apiRepository.getPagedFilteredPokemonList(type).cachedIn(viewModelScope)

    init {
        dispatch(FilterEvent.GetTypeInfo)
    }

    override fun dispatch(event: FilterEvent) {
        when (event) {
            is FilterEvent.GetTypeInfo -> getTypeInfo()
            is FilterEvent.NavigateToInfoScreen -> navigateToInfoScreen(event.name)
        }
    }

    private fun navigateToInfoScreen(name: String) = intent {
        postSideEffect(FilterSideEffect.NavigateToInfoScreen(name))
    }

    private fun getTypeInfo() = intent {
        val typeInfoResource = apiRepository.fetchFilteredByTypePokemonsList(type)
        when (typeInfoResource) {
            is Resource.Error -> {
                reduce {
                    state.copy(
                        downloadState = DownloadState.Error
                    )
                }
            }
            is Resource.Success -> {
                reduce {
                    state.copy(
                        typePokemonInfo = typeInfoResource.data,
                        downloadState = DownloadState.Success
                    )
                }
            }
        }
    }
}