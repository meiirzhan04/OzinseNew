package com.example.ozinsenew.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ozinsenew.data.room.bookmark.ListRepository
import com.example.ozinsenew.presentation.components.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun updateFirebaseEmail(newEmail: String, onResult: (Boolean, String?) -> Unit) {
        repository.updateEmail(newEmail) { success, message ->
            _state.value = _state.value.copy(
                emailUpdated = success,
                errorMessage = message
            )
            onResult(success, message)
        }
    }

}

