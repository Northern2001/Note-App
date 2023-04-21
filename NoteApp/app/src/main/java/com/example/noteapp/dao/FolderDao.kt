package com.example.noteapp.dao

import androidx.room.*
import com.example.noteapp.model.FileModel
import com.example.noteapp.model.FolderModel

@Dao
interface FolderDao {
    @Query("SELECT * FROM FolderModel")
    fun getListFolder(): List<FolderModel>


    @Query("SELECT * FROM FolderModel WHERE idFolder IN (:folderIds)")
    fun loadAllByIds(folderIds: IntArray): List<FolderModel>

    @Query("SELECT * FROM FolderModel WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): FolderModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg folder: FolderModel)

    @Delete
    fun delete(user: FolderModel)
}