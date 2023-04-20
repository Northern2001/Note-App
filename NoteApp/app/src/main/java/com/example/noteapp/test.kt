package com.example.noteapp

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.LayoutDirection

fun DiamondCornerShape(cutSize: Float) = DiamondCornerShape(CornerSize(cutSize))

class DiamondCornerShape(cornerSize: CornerSize) : CornerBasedShape(
    topStart = cornerSize,
    topEnd = cornerSize,
    bottomEnd = cornerSize,
    bottomStart = cornerSize
) {
    override fun copy(
        topStart: CornerSize,
        topEnd: CornerSize,
        bottomEnd: CornerSize,
        bottomStart: CornerSize
    ): CornerBasedShape {
        return DiamondCornerShape(topStart)
    }

    override fun createOutline(
        size: Size,
        topStart: Float,
        topEnd: Float,
        bottomEnd: Float,
        bottomStart: Float,
        layoutDirection: LayoutDirection
    ): Outline {
        val w = size.width
        val h = size.height

        val path = Path().apply {
            moveTo(0f, 3 * topStart)
            lineTo(topStart, topStart)
            lineTo(3 * topStart, 0f)
            lineTo(w - 3 * topEnd, 0f)
            lineTo(w - topEnd, topEnd)
            lineTo(w, 3 * bottomEnd)
            lineTo(w, h - 3 * bottomEnd)
            lineTo(w - bottomEnd, h - bottomEnd)
            lineTo(w - 3 * bottomStart, h)
            lineTo(3 * bottomStart, h)
            lineTo(bottomStart, h - bottomStart)
            lineTo(0f, h - 3 * bottomStart)
            close()
        }
        return Outline.Generic(path)
    }
}