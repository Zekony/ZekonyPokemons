package com.zekony.pokemons.ui.infoScreen

import com.zekony.pokemons.data.remote.Resource
import com.zekony.pokemons.domain.PokemonApiRepository
import com.zekony.pokemons.ui.MviViewModel
import com.zekony.pokemons.ui.util.DownloadState
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce


class InfoViewModel(
    private val apiRepository: PokemonApiRepository
) : MviViewModel<InfoState, InfoSideEffect, InfoEvent>(
    initialState = InfoState()
) {
    override fun dispatch(event: InfoEvent) {
        when (event) {
            is InfoEvent.FetchPokemonInfo -> makeServerRequest(event.name)
            is InfoEvent.NavigateToFilterScreen -> navigateToFilterScreen(event.type)
            is InfoEvent.FetchAbilityInfo -> fetchAbilityInfo(event.name)
            is InfoEvent.OpenAbilityInfoDialog -> openAbilityInfoDialog(event.open)
        }
    }

    private fun openAbilityInfoDialog(open: Boolean) = intent {
        reduce {
            state.copy(
                isAbilityInfoDialogOpen = open
            )
        }
        if (!open)
            reduce {
                state.copy(
                    abilityInfo = null,
                    abilityInfoDownloadState = DownloadState.Loading
                )
            }
    }

    private fun fetchAbilityInfo(name: String) = intent {
        val infoResourse = apiRepository.fetchAbilityInfo(name)
        when (infoResourse) {
            is Resource.Error -> {
                reduce {
                    state.copy(
                        abilityInfoDownloadState = DownloadState.Error
                    )
                }
            }
            is Resource.Success -> {
                reduce {
                    state.copy(
                        abilityInfo = infoResourse.data,
                        abilityInfoDownloadState = DownloadState.Success
                    )
                }
            }
        }
    }

    private fun navigateToFilterScreen(type: String) = intent {
        postSideEffect(InfoSideEffect.NavigateToFilterScreen(type))
    }

    private fun makeServerRequest(name: String) = intent {
        val response = apiRepository.fetchPokemonInfo(name)
        when (response) {
            is Resource.Error -> {
                reduce {
                    state.copy(
                        pokemonInfoDownloadState = DownloadState.Error
                    )
                }
            }
            is Resource.Success -> {
                reduce {
                    state.copy(
                        pokemonInfo = response.data,
                        pokemonInfoDownloadState = DownloadState.Success
                    )
                }
            }
        }
    }
}