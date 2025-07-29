package com.example.ozinsenew.data.room.bookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: ListItems)


    @Query("DELETE FROM listitems WHERE name = :name AND data = :data AND image = :image AND category = :category")
    suspend fun deleteByFields(name: String, data: String, image: Int, category: String)

    @Query("SELECT * FROM listitems")
    fun getAllItems(): Flow<List<ListItems>>

    @Query("SELECT * FROM listitems WHERE category = :category")
    fun getItemsByCategory(category: String): Flow<List<ListItems>>

    @Query("SELECT EXISTS(SELECT 1 FROM listitems WHERE name = :name AND data = :data AND image = :image AND category = :category)")
    suspend fun isBookmarked(name: String, data: String, image: Int, category: String): Boolean

    @Delete
    suspend fun delete(item: ListItems)

}


