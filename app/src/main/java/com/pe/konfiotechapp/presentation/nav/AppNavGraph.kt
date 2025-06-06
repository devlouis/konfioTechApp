package com.pe.konfiotechapp.presentation.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pe.konfiotechapp.presentation.Screen
import com.pe.konfiotechapp.presentation.home.screen.HomeScreen

@Composable
fun AppNavGraph () {
    val navController = rememberNavController()

    Surface (
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen()
            }
        }
    }
}