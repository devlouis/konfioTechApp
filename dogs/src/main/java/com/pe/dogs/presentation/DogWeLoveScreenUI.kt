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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.coredesign.components.AppToolbar
import com.pe.coredesign.components.TextComponent
import com.pe.coredesign.components.ViewLoading
import com.pe.dogs.R
import com.pe.dogs.presentation.state.UiState
import com.pe.dogs.presentation.viewmodel.DogViewModel

@Composable
fun DogWeLoveScreenUI(
    viewModel: DogViewModel = hiltViewModel(),
    primaryButtonClicked: () -> Unit = {}) {

    viewModel.getDogsList()
    Scaffold (
        topBar = {
            AppToolbar(
                title = stringResource(R.string.dogs_we_love),
                isBackButtonVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked()
                })
        }
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = viewModel.dogState.collectAsState().value) {
                is UiState.Idle -> { }
                is UiState.Loading -> { ViewLoading() }
                is UiState.Success -> {
                    val dogs = state.data
                    DogListScreen(
                        padding = padding,
                        dogs = dogs)
                }
                is UiState.Error -> {
                    TextComponent(
                        modifier = Modifier.wrapContentSize(),
                        textValue = "Error: ${state.message}",
                        fontSizeValue = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    apiLevel = 34
)
@Composable
fun DogWeLoveScreenUIPreview() {
    DogWeLoveScreenUI()
}