package com.example.ozinsenew.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsenew.data.room.bookmark.ListItems
import com.example.ozinsenew.data.room.bookmark.ListItemsDatabase
import com.example.ozinsenew.data.room.bookmark.ListRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {

    val allItems: StateFlow<List<ListItems>> = repository.readAllData
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    fun insert(item: ListItems) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(item)
        }
    }

    suspend fun isBookmarked(item: ListItems): Boolean {
        return repository.isBookmarked(item)
    }

    fun delete(item: ListItems) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteByFields(item)
        }
    }
}
