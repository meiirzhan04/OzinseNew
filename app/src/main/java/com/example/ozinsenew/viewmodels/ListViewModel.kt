package com.example.ozinsenew.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.room.bookmark.ListItemsDatabase
import com.example.ozinsenew.room.bookmark.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: Flow<List<ListItems>>
    private val repository: ListRepository

    init {
        val listItemsDao = ListItemsDatabase.getDatabase(application).listItemsDao()
        repository = ListRepository(listItemsDao)
        readAllData = repository.readAllData
    }

    val allItems: StateFlow<List<ListItems>> = repository.allItems
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insert(item: ListItems) {
        viewModelScope.launch {
            repository.addListItem(item)
        }
    }
}