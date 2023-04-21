package com.example.noteapp.dao

import androidx.room.*
import com.example.noteapp.model.FileModel


@Dao
interface FileDao {
    @Query("SELECT * FROM FileModel")
    fun getListFile(): List<FileModel>

    @Insert
    fun insertAll(vararg folder: FileModel)

    @Delete
    fun delete(user: FileModel)
    @Query("SELECT * FROM filemodel WHERE id_folder =:id ")
    fun findListWithId(id: String): List<FileModel>

    @Query("SELECT * FROM filemodel WHERE idFile == :id")
    fun getTour(id: Int): FileModel

    @Update
    fun updateTour(tour: FileModel?): Int
    fun updateTour(id: Int, title: String,content : String) {
        val tour: FileModel = getTour(id)
        tour.title = title
        tour.content = content
        updateTour(tour)
    }


}