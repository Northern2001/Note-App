package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FileModel(
    @PrimaryKey val idFile: Int? = null,
    @ColumnInfo(name = "id_folder") var idFolder: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
)