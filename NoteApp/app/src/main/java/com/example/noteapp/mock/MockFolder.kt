package com.example.noteapp.mock

import com.example.noteapp.model.FolderModel

object MockFolder {
    var listFolder = listOf(
        FolderModel(title  ="All(20)"),
        FolderModel(title="Bookmarked"),
        FolderModel(title="Class"),
    )
}