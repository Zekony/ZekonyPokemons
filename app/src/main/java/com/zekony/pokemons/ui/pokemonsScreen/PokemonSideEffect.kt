package com.zekony.pokemons.ui.pokemonsScreen

sealed interface PokemonSideEffect {
    class NavigateToInfoScreen(val name: String): PokemonSideEffect
}