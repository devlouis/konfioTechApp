package com.pe.dogs.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.coredesign.components.TextComponent
import com.pe.coredesign.components.ViewLoading
import com.pe.dogs.presentation.state.UiState
import com.pe.dogs.presentation.viewmodel.DogViewModel

@Composable
fun DogWeLoveScreenUI(viewModel: DogViewModel = hiltViewModel()) {
    val dogState by viewModel.dogState.collectAsState()

    viewModel.getDogsList()
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = viewModel.dogState.collectAsState().value) {
                is UiState.Idle -> { /* Mostrar pantalla vacía o inicial */ }
                is UiState.Loading -> { ViewLoading() }
                is UiState.Success -> {
                    val dogs = state.data
                    TextComponent(
                        modifier = Modifier.wrapContentSize(),
                        textValue = dogs.toString(),
                        fontSizeValue = 16.sp
                    )
                }
                is UiState.Error -> {
                    Text("Error: ${state.message}")
                }
            }
        }
    }

}