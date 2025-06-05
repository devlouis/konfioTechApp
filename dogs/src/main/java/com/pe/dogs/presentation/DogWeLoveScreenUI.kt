package com.pe.dogs.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.dogs.presentation.state.UiState
import com.pe.dogs.presentation.viewmodel.DogViewModel

@Composable
fun DogWeLoveScreenUI(viewModel: DogViewModel = hiltViewModel()) {
    val dogState by viewModel.dogState.collectAsState()

    viewModel.getDogsList()
    Scaffold { padding ->
        when (val state = viewModel.dogState.collectAsState().value) {
            is UiState.Idle -> { /* Mostrar pantalla vacía o inicial */ }
            is UiState.Loading -> { /* Mostrar loader */ }
            is UiState.Success -> {
                val dogs = state.data
                Text("Error: $dogs")
                // Mostrar lista de perros
            }
            is UiState.Error -> {
                Text("Error: ${state.message}")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

        }
    }

}