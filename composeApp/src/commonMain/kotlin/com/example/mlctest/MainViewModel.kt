package com.example.mlctest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {
    private val _mainScreenUiState = MutableStateFlow(MainScreenUiState())
    val mainScreenUiState = _mainScreenUiState.asStateFlow()

    fun toggleRandomVariable() {
        _mainScreenUiState.update { it.copy(randomVariable = !it.randomVariable) }
    }
}