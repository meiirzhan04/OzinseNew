package com.example.ozinsenew.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.ozinsenew.data.login.Login
import com.example.ozinsenew.data.login.Register
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class ViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var isAuthenticated by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>("Not")
        private set

    fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isAuthenticated = task.isSuccessful
                if (!task.isSuccessful) {
                    errorMessage = task.exception?.message
                }
            }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isAuthenticated = task.isSuccessful
                if (!task.isSuccessful) {
                    errorMessage = task.exception?.message
                }
            }
    }

    fun logout() {
        auth.signOut()
        isAuthenticated = false
    }
}
