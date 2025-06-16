package com.example.ozinsenew.room.bookmark

import androidx.room.Entity
import kotlinx.coroutines.flow.Flow


@Entity
class ListRepository(private val listItemsDao: ListItemsDao) {
    val readAllData: Flow<List<ListItems>> = listItemsDao.readAllData()
    val allItems = listItemsDao.readAllData()

    suspend fun addListItem(item: ListItems) {
        listItemsDao.addListItem(item)
    }
    suspend fun delete(item: ListItems) {
        listItemsDao.deleteItems(item)
    }
}