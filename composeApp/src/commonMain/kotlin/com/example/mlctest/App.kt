package com.example.mlctest

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val isExpanded = remember { mutableStateOf(false) }
        val density = LocalDensity.current
        var boxHeight by remember { mutableStateOf(0.dp) }
        var clickedPosition by remember { mutableStateOf(DpOffset.Unspecified) }

        Box(Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        with(density) {
                            val x = it.x.toDp()
                            val y = it.y.toDp()
                            clickedPosition = DpOffset(x, y)
                            isExpanded.value = true
                        }
                    })
                }
                .onSizeChanged {
                    with(density) {
                        val size = DpSize(it.width.toDp(), it.height.toDp())
                        boxHeight = size.height
                    }
                },
            ) {}
            SampleMenu(isExpanded, boxHeight, clickedPosition)
        }
    }
}

@Composable
fun SampleMenu(isExpanded: MutableState<Boolean>, boxHeight: Dp, clickedPosition: DpOffset) {
    DropdownMenu(
        expanded = isExpanded.value,
        properties = PopupProperties(focusable = false),
        offset = DpOffset(clickedPosition.x, clickedPosition.y-boxHeight),
        onDismissRequest = { isExpanded.value = false }
    ) {
        DropdownMenuItem(
            text = { Text(text = "1") },
            onClick = {}
        )
        DropdownMenuItem(
            text = { Text(text = "2") },
            onClick = {}
        )
        DropdownMenuItem(
            text = { Text(text = "3") },
            onClick = {}
        )
    }
}