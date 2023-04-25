package com.example.noteapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.noteapp.R
import com.example.noteapp.nav.DestinationWithParam
import com.example.noteapp.nav.RouterManager
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val rootController = RouterManager.current.rootController

    LaunchedEffect(Unit ){
        delay(500)
        rootController?.navigate(
            DestinationWithParam.getHomeParams()
        )
    }
    Image(
        painter = painterResource(id = R.drawable.galaxylogo),
        contentDescription = "",
        modifier = Modifier.fillMaxSize()
    )
}