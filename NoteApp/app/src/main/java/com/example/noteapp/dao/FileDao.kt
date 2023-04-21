package com.example.noteapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.noteapp.model.FileModel
import com.example.noteapp.model.FolderModel

@Dao
interface FileDao {
    @Query("SELECT * FROM FileModel")
    fun getListFile(): List<FileModel>

    @Insert
    fun insertAll(vararg folder: FileModel)

    @Delete
    fun delete(user: FileModel)

}