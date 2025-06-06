package com.pe.konfiotechapp.presentation.home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pe.coredesign.components.AppToolbar
import com.pe.coredesign.theme.AppColors.Background

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            AppToolbar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .background(Background)
                .padding(innerPadding)
        ) {

        }

    }
}


@Preview(
    showBackground = true,
    apiLevel = 34 // <= estable y compatible
)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}