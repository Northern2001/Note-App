package com.example.noteapp.dao

import androidx.room.*
import com.example.noteapp.model.FolderModel

@Dao
interface FolderDao {
    @Query("SELECT * FROM FolderModel")
    fun getListFolder(): List<FolderModel>


    @Query("SELECT * FROM FolderModel WHERE idFolder IN (:folderIds)")
    fun loadAllByIds(folderIds: IntArray): List<FolderModel>

    @Query("SELECT * FROM FolderModel WHERE title LIKE :title LIMIT 1")
    fun findByName(title: String): FolderModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg folder: FolderModel)

    @Delete
    fun delete(user: FolderModel)

    @Query("DELETE FROM FolderModel WHERE idFolder NOT IN (:newIds)")
    fun deleteOutdatedAccounts(newIds: List<Int>)

    // To insert/update all accounts from the new list
// Thanks to OnConflictStrategy.REPLACE strategy you get both insert and update
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUpdateAccounts(accounts: List<FolderModel>)

    // To do two methods above in single transaction
    @Transaction
    fun refreshFolder(newList: List<FolderModel>) {
        deleteOutdatedAccounts(newList.map { it.idFolder ?: 0 })
        insertUpdateAccounts(newList)
    }
}