package com.pe.konfiotechapp.presentation.home.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.pe.coredesign.components.AppToolbar
import com.pe.coredesign.theme.AppColors
import com.pe.konfiotechapp.R
import com.pe.coredesign.components.BannerComponent
import com.pe.coredesign.components.SnackBar.SnackbarHostSuccessCustom
import com.pe.dogs.presentation.state.UiState
import com.pe.dogs.presentation.viewmodel.DogViewModel
import com.pe.utilities.logging.AppLogger
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    viewModel: DogViewModel = hiltViewModel(),
    primaryButtonClicked: () -> Unit = {},
    dogBannerOnClick: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.dogBDEvent.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }
    Scaffold(
        topBar = {
            AppToolbar(title = stringResource(R.string.home),
                isSyncVisible = true,
                primaryButtonClicked = {
                    primaryButtonClicked()
                },
                syncClicked = {
                        viewModel.clearDogs()

                })
        },
        snackbarHost = {
            SnackbarHostSuccessCustom(snackbarHostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
                .background(AppColors.Background)
                .padding(innerPadding)
        ) {
            BannerComponent(
                title = stringResource(R.string.dogs_we_love),
                description = stringResource(R.string.click_here),
                resourceValue = R.drawable.we_love_dogs,
                bannerOnClick = {
                    dogBannerOnClick()
                }
                /*imageUrl = "https://static.wixstatic.com/media/b7bd22_334301ac02ab4c62b68e4729e635b18f~mv2.webp/v1/fill/w_340,h_158,al_c,q_80,usm_0.66_1.00_0.01,enc_avif,quality_auto/We%20Love%20Dogs%20Logo.webp"*/
            )
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