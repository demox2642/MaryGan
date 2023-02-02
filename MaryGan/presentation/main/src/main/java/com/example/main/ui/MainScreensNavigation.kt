package com.example.main.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.main.ui.screens.MainScreen

sealed class MainScreensNavigation(
    val route: String
) {
    object MainScreen : MainScreensNavigation("main")
}

fun NavGraphBuilder.mainScreens(navHostController: NavHostController) {
    composable(MainScreensNavigation.MainScreen.route) { MainScreen(navHostController) }
}
