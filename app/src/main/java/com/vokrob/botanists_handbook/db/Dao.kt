package com.vokrob.botanists_handbook.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vokrob.botanists_handbook.utils.ListItem

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)

    @Delete
    suspend fun deleteItem(item: ListItem)

    @Query("SELECT * FROM main WHERE category LIKE :cat")
    suspend fun getAllItemsByCategory(cat: String): List<ListItem>
}

























