package com.pe.coredesign.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = AppColors.Primary,
            secondary = AppColors.Secondary,
            background = AppColors.WhiteColor,
            error = AppColors.Error
        ),
        typography = AppTypography,
        content = content
    )
}