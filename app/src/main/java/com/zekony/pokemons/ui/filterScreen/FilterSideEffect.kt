package com.zekony.pokemons.ui.filterScreen

sealed interface FilterSideEffect {
    class NavigateToInfoScreen(val name: String) : FilterSideEffect
}