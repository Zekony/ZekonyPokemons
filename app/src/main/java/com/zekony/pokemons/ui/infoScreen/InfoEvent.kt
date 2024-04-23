package com.zekony.pokemons.ui.infoScreen

sealed interface InfoEvent {
    class FetchPokemonInfo(val name: String) : InfoEvent
    class NavigateToFilterScreen(val type: String) : InfoEvent
    class FetchAbilityInfo(val name: String) : InfoEvent
    class OpenAbilityInfoDialog(val open: Boolean) : InfoEvent
}