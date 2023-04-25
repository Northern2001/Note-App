package com.example.noteapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.noteapp.model.FileModel
import com.example.noteapp.model.FolderModel

class FolderViewModel : ViewModel() {
    var listFile by mutableStateOf(listOf<FileModel>())
    var listFolder by mutableStateOf(listOf<FolderModel>())
    var modelFolder by mutableStateOf(FolderModel())
    var fileModel by mutableStateOf(FileModel())

    fun reloadListFolder() {
        val dummy = listFolder
        listFolder = arrayListOf()
        listFolder = dummy
    }

    fun reloadListFile() {
        val dummy = listFile
        listFile = arrayListOf()
        listFile = dummy
    }
}

