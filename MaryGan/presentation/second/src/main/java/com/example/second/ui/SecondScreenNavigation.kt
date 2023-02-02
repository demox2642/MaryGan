package com.example.second.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.second.ui.screens.SecondScreen

sealed class SecondScreenNavigation(
    val route: String
) {
    object SecondScreen : SecondScreenNavigation("second")
}

fun NavGraphBuilder.secondScreens(navHostController: NavHostController) {
    composable(SecondScreenNavigation.SecondScreen.route) { SecondScreen(navHostController) }
}
