package com.example.base_ui.theme // ktlint-disable package-name

import androidx.compose.runtime.*

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    @Composable
    fun AppTheme(
        colors: AppColors = AppTheme.colors,
        typography: AppTypography = AppTheme.typography,
        content: @Composable () -> Unit
    ) {
        val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }
        CompositionLocalProvider(
            LocalColors provides rememberedColors,
            LocalTypography provides typography
        ) {
            content()
        }
    }

    internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
}
