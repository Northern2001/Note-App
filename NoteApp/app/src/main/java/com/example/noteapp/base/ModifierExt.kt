package com.example.noteapp.base

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.noteapp.ui.theme.colorItemFolder


fun Modifier.backGroundGradient(
    colors: List<Color> = listOf(
        colorItemFolder.copy(alpha = 0.2f),
        colorItemFolder
    ),
    isHorizontal: Boolean = false,
    alpha: Float = 1f

): Modifier {
    return this
        .background(
            brush = if (isHorizontal) Brush.horizontalGradient(
                colors = colors,
            ) else Brush.verticalGradient(
                colors = colors
            ),
            alpha = alpha
        )
}