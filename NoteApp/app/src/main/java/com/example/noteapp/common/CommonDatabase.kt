package com.example.noteapp.common

import android.content.Context
import androidx.room.Room
import com.example.noteapp.database.AppDatabase

class CommonDatabase private constructor()  {

    companion object {
        val current = CommonDatabase()
    }

    fun getDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "galaxy-1"
        ).allowMainThreadQueries().build()

}