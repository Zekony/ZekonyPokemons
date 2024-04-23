package com.zekony.pokemons.ui.filterScreen

sealed interface FilterEvent {
    object GetTypeInfo: FilterEvent
    class NavigateToInfoScreen(val name: String): FilterEvent
}