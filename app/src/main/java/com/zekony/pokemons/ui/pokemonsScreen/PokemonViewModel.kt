package com.zekony.pokemons.ui.pokemonsScreen

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zekony.pokemons.data.entity.Pokemon
import com.zekony.pokemons.domain.PokemonApiRepository
import com.zekony.pokemons.ui.MviViewModel
import kotlinx.coroutines.flow.Flow
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class PokemonViewModel(
    private val apiRepository: PokemonApiRepository,
) : MviViewModel<Any, PokemonSideEffect, PokemonEvent>(
    initialState = Any()
) {
    fun getPagingPokemonsList(): Flow<PagingData<Pokemon>> =
        apiRepository
            .getPagedPokemonList()
            .cachedIn(viewModelScope)

    override fun dispatch(event: PokemonEvent) {
        when (event) {
            is PokemonEvent.NavigateToInfoScreen -> navigateToInfoScreen(event.name)
        }
    }

    private fun navigateToInfoScreen(name: String) = intent {
        postSideEffect(PokemonSideEffect.NavigateToInfoScreen(name))
    }
}


