package com.example.noteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.noteapp.dao.FileDao
import com.example.noteapp.dao.FolderDao
import com.example.noteapp.model.FileModel
import com.example.noteapp.model.FolderModel

@Database(entities = [FolderModel::class, FileModel::class], version = 2)

abstract class AppDatabase : RoomDatabase() {
    abstract fun daoFolder(): FolderDao
    abstract fun daoFile(): FileDao
}