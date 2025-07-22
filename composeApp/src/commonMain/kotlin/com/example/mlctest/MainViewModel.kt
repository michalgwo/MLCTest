package com.example.mlctest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _mainScreenUiState = MutableStateFlow(MainScreenUiState())
    val mainScreenUiState = _mainScreenUiState.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(3000)
                toggleRandomVariable()
            }
        }
    }

    fun toggleRandomVariable() {
        _mainScreenUiState.update { it.copy(randomVariable = !it.randomVariable) }
    }
}