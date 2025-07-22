package com.example.mlctest

import androidx.compose.runtime.Immutable

@Immutable
data class MainScreenUiState(
    val style: String = "https://api.protomaps.com/styles/v5/light/en.json?key=73c45a97eddd43fb",
    val randomVariable: Boolean = false
)