package com.example.noteapp.nav

import androidx.navigation.NavController

class RouterManager {
    var rootController: NavController? = null

    companion object {
        val current = RouterManager()
    }
}