package com.example.ozinsenew.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsenew.data.room.bookmark.ListItems
import com.example.ozinsenew.data.room.bookmark.ListItemsDatabase
import com.example.ozinsenew.data.room.bookmark.ListRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val listItemsDao = ListItemsDatabase.getDatabase(application).listItemsDao()
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val repository = ListRepository(
        firebaseAuth = firebaseAuth,
        listItemsDao = listItemsDao
    )

    val allItems: StateFlow<List<ListItems>> = repository.readAllData
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun insert(item: ListItems) {
        viewModelScope.launch(Dispatchers.IO) {
            listItemsDao.insert(item)
        }
    }

    suspend fun isBookmarked(item: ListItems): Boolean {
        return listItemsDao.isBookmarked(item.name, item.data, item.image, item.category)
    }

    fun delete(item: ListItems) {
        viewModelScope.launch(Dispatchers.IO) {
            listItemsDao.deleteByFields(item.name, item.data, item.image, item.category)
        }
    }
}