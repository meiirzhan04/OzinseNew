package com.example.ozinsenew.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinsenew.data.room.bookmark.ListRepository
import com.example.ozinsenew.presentation.components.AuthState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: ListRepository,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow(
        AuthState(
            currentUser = firebaseAuth.currentUser,
            isUserLoggedIn = firebaseAuth.currentUser != null
        )
    )
    val isAuthenticated: StateFlow<Boolean> = _state
        .map { it.isUserLoggedIn }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            firebaseAuth.currentUser != null
        )
    val state: StateFlow<AuthState> = _state.asStateFlow()

    init {
        firebaseAuth.addAuthStateListener { auth ->
            _state.value = _state.value.copy(
                currentUser = auth.currentUser,
                isUserLoggedIn = auth.currentUser != null
            )
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            if (result.successful) {
                _state.value = _state.value.copy(errorMessage = null)
            } else {
                _state.value = _state.value.copy(errorMessage = result.message)
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.register(email, password)
            if (result.successful) {
                _state.value = _state.value.copy(errorMessage = null)
            } else {
                _state.value = _state.value.copy(errorMessage = result.message)
            }
        }
    }

    fun resetJustRegistered() {
        _state.value = _state.value.copy(isUserLoggedIn = false)
    }

    fun logout() {
        repository.logout()
    }
}
