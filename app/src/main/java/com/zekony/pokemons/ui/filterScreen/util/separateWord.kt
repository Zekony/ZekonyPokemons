package com.zekony.pokemons.ui.filterScreen.util

fun String.separateWord(): String =
    this.replace(Regex("[A-Z]")) {
        " ${it.value}"
    }