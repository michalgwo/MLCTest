package com.example.mlctest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.layer.LineLayer
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.rememberStyleState
import dev.sargunv.maplibrecompose.compose.source.rememberGeoJsonSource
import dev.sargunv.maplibrecompose.core.BaseStyle
import dev.sargunv.maplibrecompose.core.source.GeoJsonData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val viewModel: MainViewModel = viewModel()
        val uiState by viewModel.mainScreenUiState.collectAsStateWithLifecycle()

        val cameraState = rememberCameraState()
        val styleState = rememberStyleState()

        LaunchedEffect(cameraState.isCameraMoving) {
            //do something
        }

        Box {
            MaplibreMap(
                cameraState = cameraState,
                styleState = styleState,
                baseStyle = BaseStyle.Uri(uiState.style),
            ) {
                LineLayer(
                    id = "asdf",
                    source = rememberGeoJsonSource(
                        GeoJsonData.Uri("https://raw.githubusercontent.com/datanews/amtrak-geojson/refs/heads/master/amtrak-combined.geojson")
                    )
                )
            }
//            Text(
//                modifier = Modifier.align(Alignment.TopStart),
//                text = cameraState.position.zoom.toString()
//            )
            Button(
                modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 40.dp),
                onClick = {
                    viewModel.toggleRandomVariable()
                }
            ) {
                Text(text = "Toggle random variable")
            }
        }
    }
}