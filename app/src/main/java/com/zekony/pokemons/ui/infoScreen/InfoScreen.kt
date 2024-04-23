package com.zekony.pokemons.ui.infoScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zekony.pokemons.R
import com.zekony.pokemons.ui.infoScreen.composables.AbilitiesColumn
import com.zekony.pokemons.ui.infoScreen.composables.AbilityDescriptionDialog
import com.zekony.pokemons.ui.infoScreen.composables.TypesColumn
import com.zekony.pokemons.ui.util.DownloadState

@Composable
fun InfoScreen(
    state: InfoState,
    onEvent: (InfoEvent) -> Unit
) {
    if (state.isAbilityInfoDialogOpen) AbilityDescriptionDialog(state, onEvent)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)
    ) {
        when (state.pokemonInfoDownloadState) {
            DownloadState.Error -> Text(text = stringResource(R.string.error))
            DownloadState.Loading -> CircularProgressIndicator(color = Color.White)
            DownloadState.Success -> InfoScreenContent(state, onEvent)
        }
    }
}

@Composable
fun InfoScreenContent(state: InfoState, onEvent: (InfoEvent) -> Unit) {
    if (state.pokemonInfo != null)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            with(state.pokemonInfo) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = name.replaceFirstChar { it.uppercase() },
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                AbilitiesColumn(abilities, onEvent)
                TypesColumn(types, onEvent)
            }
        }
}