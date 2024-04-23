package com.zekony.pokemons.ui.pokemonsScreen

sealed interface PokemonEvent {
    class NavigateToInfoScreen(val name: String) : PokemonEvent
}