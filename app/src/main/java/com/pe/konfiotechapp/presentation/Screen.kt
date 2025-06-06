package com.pe.konfiotechapp.presentation

sealed class Screen (val route: String) {
    data object HomeScreen: Screen(route = "home_screen")
    data object DogsScreen: Screen(route = "dogs_screen")
}