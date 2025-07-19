package com.example.mlctest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.layer.LineLayer
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.rememberStyleState
import dev.sargunv.maplibrecompose.compose.source.rememberGeoJsonSource
import dev.sargunv.maplibrecompose.core.BaseStyle
import dev.sargunv.maplibrecompose.core.source.GeoJsonData
import io.github.dellisd.spatialk.geojson.Position
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Duration.Companion.milliseconds

@Composable
@Preview
fun App() {
    MaterialTheme {
        val cameraState = rememberCameraState()
        val styleState = rememberStyleState()
        Box {
            MaplibreMap(
                cameraState = cameraState,
                styleState = styleState,
                baseStyle = remember { BaseStyle.Uri("https://api.protomaps.com/styles/v5/light/en.json?key=73c45a97eddd43fb") },
            ) {
                LineLayer(
                    id = "asdf",
                    source = rememberGeoJsonSource(
                        GeoJsonData.Uri("https://raw.githubusercontent.com/datanews/amtrak-geojson/refs/heads/master/amtrak-combined.geojson")
                    )
                )
            }
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = cameraState.position.zoom.toString()
            )
            val scope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current
            TextField(
                modifier = Modifier.align(Alignment.TopStart).padding(top = 20.dp),
                value = "",
                onValueChange = {}
            )
            Button(
                modifier = Modifier.align(Alignment.TopEnd).padding(top = 20.dp),
                onClick = {
                    scope.launch {
                        cameraState.animateTo(
                            finalPosition = cameraState.position.copy(
                                target = Position(19.938830100326083, 50.061658517346814),
                                zoom = 13.0
                            ),
                            duration = 1000.milliseconds
                        )
                    }
                    keyboardController?.hide()
                },
                content = {
                    Text("Animate camera")
                }
            )
        }
    }
}