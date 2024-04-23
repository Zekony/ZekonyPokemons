package com.zekony.pokemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.zekony.pokemons.ui.navigation.Navigation
import com.zekony.pokemons.theme.PokemonTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonTheme {
                Navigation()
            }
        }
    }
}