package com.pe.coredesign.components.SnackBar

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SnackbarHostSuccessCustom(snackbarHostState: SnackbarHostState) {

    SnackbarHost(hostState = snackbarHostState) { data ->
        Snackbar(
            containerColor = Color.Green, // Fondo rojo para error
            contentColor = Color.White,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(data.visuals.message)
        }
    }

}