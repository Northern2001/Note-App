package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.dao.FileDao
import com.example.noteapp.dao.FolderDao
import com.example.noteapp.model.FileModel
import com.example.noteapp.model.FolderModel

@Database(entities = [FolderModel::class, FileModel::class], version = 2, exportSchema = true)

abstract class AppDatabase : RoomDatabase() {
    abstract fun daoFolder(): FolderDao
    abstract fun daoFile(): FileDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "notes_database"
            ).build()
        }
    }
}