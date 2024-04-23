package com.zekony.pokemons.ui.util

sealed class DownloadState {
    object Success : DownloadState()
    object Loading : DownloadState()
    object Error : DownloadState()
}