package com.pe.coredesign.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = AppColors.TextPrimary
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        color = AppColors.TextPrimary
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        color = AppColors.TextSecondary
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        color = AppColors.TextSecondary
    )
)