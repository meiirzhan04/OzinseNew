package com.example.ozinsenew.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsenew.R
import com.example.ozinsenew.domain.model.home.BoxData
import com.example.ozinsenew.data.room.bookmark.ListItemsDatabase
import com.example.ozinsenew.data.room.bookmark.ListRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application, private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val repository: ListRepository

    init {
        val dao = ListItemsDatabase.getDatabase(application).listItemsDao()
        repository = ListRepository(firebaseAuth, dao)
    }

    private val _headBoxData = listOf(
        BoxData(
            0,
            R.drawable.ic_page_1,
            "Қызғалдақтар мекені",
            "Мақалада қызғалдақтардың отаны Қазақстан екені айтылады.",
            "head"
        ),
        BoxData(
            1,
            R.drawable.ic_page_2,
            "Ойыншықтар",
            "5 жасар Алуаның ойыншықтары өте көп.",
            "head"
        )
    )

    private val _middleBoxData = listOf(
        BoxData(2, R.drawable.image_globys, "Глобус", "2-бөлім", "middle"),
        BoxData(3, R.drawable.image_natural, "Табиғат сақшылары", "4-бөлім", "middle")
    )

    private val _boxData = listOf(
        BoxData(4, R.drawable.image_aidar, "Айдар", "Мультсериал", "box"),
        BoxData(5, R.drawable.image_car, "Суперкөлік Самұрық", "Мультсериал", "box"),
        BoxData(6, R.drawable.image_cinema, "Каникулы off-line 2", "Телехикая", "box")
    )

    val headBoxData: List<BoxData> = _headBoxData
    val middleBoxData: List<BoxData> = _middleBoxData
    val boxData: List<BoxData> = _boxData

    private val _justRegistered = MutableStateFlow(false)
    val justRegistered: StateFlow<Boolean> = _justRegistered

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    fun getBoxById(id: Int): BoxData? {
        return (headBoxData + middleBoxData + boxData).find { it.id == id }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            _isAuthenticated.value = result.successful
            _errorMessage.value = result.message
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.register(email, password)
            if (result.successful) {
                _justRegistered.value = true
                _isAuthenticated.value = false
            } else {
                _errorMessage.value = result.message
            }
        }
    }

    fun resetJustRegistered() {
        _justRegistered.value = false
    }


    fun logout() {
        repository.logout()
        _isAuthenticated.value = false
    }

    fun updateFirebaseEmail(newEmail: String, onResult: (Boolean, String?) -> Unit) {
        repository.updateEmail(newEmail, onResult)
    }

    fun allBoxData(): List<BoxData> {
        return headBoxData + middleBoxData + boxData
    }
}
