package com.example.ozinsenew.room.bookmark

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ListItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListItem(item: ListItems)

    @Delete
    suspend fun deleteItems(item: ListItems)

    @Query("SELECT * FROM ListItems")
    fun readAllData(): Flow<List<ListItems>>

}

