package com.example.noteapp.screen.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.base.BaseBackground
import com.example.noteapp.base.CircleImage
import com.example.noteapp.nav.RouterManager

@Composable
fun DetailScreen() {
    val rootController = RouterManager.current.rootController
    var value by remember { mutableStateOf("") }

    BaseBackground() {
        Row(Modifier.padding(top = 12.dp)) {
            CircleImage(icon = R.drawable.ic_back) {
                rootController?.popBackStack()
            }
            Spacer(modifier = Modifier.weight(1f))
            CircleImage(icon = R.drawable.ic_share) {
                rootController?.popBackStack()
            }
            Spacer(modifier = Modifier.width(10.dp))
            CircleImage(icon = R.drawable.ic_save) {
                rootController?.popBackStack()
            }
        }
        TextField(value = value, onValueChange = {
            value = it
        })
    }
}