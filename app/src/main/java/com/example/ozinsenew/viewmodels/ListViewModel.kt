package com.example.ozinsenew.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.room.bookmark.ListItemsDao
import com.example.ozinsenew.room.bookmark.ListItemsDatabase
import com.example.ozinsenew.room.bookmark.ListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


class ListViewModel(application: Application) : AndroidViewModel(application)  {
    private val readAllData: Flow<List<ListItems>>
    private val repository: ListRepository
    private val listItemsDao = ListItemsDatabase.getDatabase(application).listItemsDao()

    init {
        val listItemsDao = ListItemsDatabase.getDatabase(application).listItemsDao()
        repository = ListRepository(listItemsDao)
        readAllData = repository.readAllData
    }

    private val CHECKED_KEY = booleanPreferencesKey("is_checked")
    private val Context.dataStore by preferencesDataStore(name = "setting")
    private val dataStore = application.dataStore

    val allItems = listItemsDao.getAllItems()


    fun toggleCheckmark() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                val current = preferences[CHECKED_KEY] == true
                preferences[CHECKED_KEY] = !current
            }
        }
    }



    fun getItemsByCategory(category: String): Flow<List<ListItems>> {
        return listItemsDao.getItemsByCategory(category)
    }

    fun insert(item: ListItems) {
        viewModelScope.launch {
            listItemsDao.insert(item)
        }
    }


    suspend fun isBookmarked(item: ListItems): Boolean {
        return listItemsDao.isBookmarked(item.name, item.data, item.image, item.category)
    }

    fun delete(item: ListItems) {
        viewModelScope.launch {
            listItemsDao.deleteByFields(item.name, item.data, item.image, item.category)
        }
    }
}