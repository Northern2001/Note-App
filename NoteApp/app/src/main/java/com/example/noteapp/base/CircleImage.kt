package com.example.noteapp.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.noteapp.ui.theme.black

@Composable
fun CircleImage(
    icon: Int,
    modifier: Modifier = Modifier
        .size(56.dp)
        .clip(RoundedCornerShape(28.dp))
        .background(color = black),
    onclick: () -> Unit
) {
    Box(
        modifier = modifier.then(Modifier.clickable {
            onclick()
        })
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}