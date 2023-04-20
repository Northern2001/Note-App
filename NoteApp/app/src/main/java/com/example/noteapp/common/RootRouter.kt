package com.example.noteapp.common

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.example.noteapp.nav.RouterManager
import com.example.noteapp.nav.authGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoilApi
@InternalCoroutinesApi
@ExperimentalComposeUiApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun RootRouter() {
    val navController = rememberAnimatedNavController()
    RouterManager.current.rootController = navController
    AnimatedNavHost(navController, startDestination = "AUTH_GRAPH") {
        authGraph()
    }
}

