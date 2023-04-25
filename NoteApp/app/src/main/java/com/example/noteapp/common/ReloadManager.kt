package com.example.noteapp.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.noteapp.model.FileModel

class ReloadManager private constructor() {
    var listFileUpdate by mutableStateOf<List<FileModel>?>(null)

    companion object {
        val current = ReloadManager()
    }


}