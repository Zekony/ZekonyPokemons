package com.zekony.pokemons.ui.infoScreen.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.zekony.pokemons.R
import com.zekony.pokemons.ui.infoScreen.InfoEvent
import com.zekony.pokemons.ui.infoScreen.InfoState
import com.zekony.pokemons.ui.util.DownloadState

@Composable
fun AbilityDescriptionDialog(state: InfoState, onEvent: (InfoEvent) -> Unit) {
    Dialog(onDismissRequest = { onEvent(InfoEvent.OpenAbilityInfoDialog(false)) }) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.5f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ) {
                when (state.abilityInfoDownloadState) {
                    DownloadState.Error -> Text(text = stringResource(id = R.string.error))
                    DownloadState.Loading -> CircularProgressIndicator(color = Color.White)
                    DownloadState.Success -> { val text = if (state.abilityInfo?.effectEntries?.any { it.language.name == "en" } == true) {
                            state.abilityInfo.effectEntries.first { it.language.name == "en" }.effect
                        } else {
                            stringResource(R.string.no_info)
                        }
                        Text(
                            text = text,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
    }
}