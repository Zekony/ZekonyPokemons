package com.zekony.pokemons.ui.infoScreen

sealed interface InfoSideEffect {
    class NavigateToFilterScreen(val type: String) : InfoSideEffect

}