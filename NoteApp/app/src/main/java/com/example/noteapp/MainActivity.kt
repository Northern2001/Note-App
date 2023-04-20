@file:OptIn(InternalCoroutinesApi::class)

package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.example.noteapp.common.RootRouter
import com.facebook.CallbackManager
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : ComponentActivity() {
    private var callbackManager = CallbackManager.Factory.create()

    @OptIn(
        ExperimentalAnimationApi::class, ExperimentalMaterialApi::class,
        ExperimentalFoundationApi::class, InternalCoroutinesApi::class,
        ExperimentalComposeUiApi::class, ExperimentalPagerApi::class, ExperimentalCoilApi::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                RootRouter()
            }
        }
    }
}
