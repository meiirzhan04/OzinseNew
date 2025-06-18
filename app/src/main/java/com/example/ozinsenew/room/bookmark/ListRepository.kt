package com.example.ozinsenew.room.bookmark

import androidx.room.Entity
import kotlinx.coroutines.flow.Flow


@Entity
class ListRepository(private val listItemsDao: ListItemsDao) {
    val readAllData: Flow<List<ListItems>> = listItemsDao.getAllItems()
    val allItems = listItemsDao.getAllItems()

    suspend fun addListItem(item: ListItems) {
        listItemsDao.insert(item)
    }

    suspend fun delete(item: ListItems) {
        listItemsDao.deleteByFields(
            name = item.name,
            data = item.data,
            image = item.image,
            category = item.category
        )
    }


}