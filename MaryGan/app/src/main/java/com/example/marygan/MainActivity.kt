package com.example.marygan

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.base_ui.theme.AppTheme
import com.example.base_ui.theme.appDarkColors
import com.example.base_ui.theme.appLightColors
import com.example.main.ui.MainScreensNavigation
import com.example.main.ui.mainScreens
import com.example.second.ui.secondScreens
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                this.window.statusBarColor = ContextCompat.getColor(this, R.color.light_gray)
                val darkTheme: Boolean = isSystemInDarkTheme()
                val colors = if (darkTheme) appDarkColors() else appLightColors()

                AppTheme.AppTheme(colors = colors) {
                    SystemUi(windows = window)
                    Surface(color = MaterialTheme.colors.background) {
                        MainContent()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}

@Composable
fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        backgroundColor = AppTheme.colors.systemBackgroundPrimary
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainScreensNavigation.MainScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            mainScreens(navController)
            secondScreens(navController)
        }
    }
}

@Composable
fun SystemUi(windows: Window) =
    AppTheme.AppTheme {
        windows.statusBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()
        windows.navigationBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
