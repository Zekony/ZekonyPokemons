package com.zekony.pokemons.ui.navigation


sealed class Screens(val route: String) {
    object Loading : Screens(
        route = "loading"
    )

    object Aviador : Screens(
        route = "aviador"
    )

    object WebViewScreen : Screens(
        route = "webView"
    )
}
