package com.example.ozinsenew.room.bookmark

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

data class AuthResult(
    val successful: Boolean,
    val message: String = ""
)

class ListRepository(
    private val firebaseAuth: FirebaseAuth,
    private val listItemsDao: ListItemsDao
) {

    suspend fun register(email: String, password: String): AuthResult {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            AuthResult(true)
        } catch (e: Exception) {
            AuthResult(false, e.message ?: "Тіркелу кезінде қате орын алды")
        }
    }

    suspend fun login(email: String, password: String): AuthResult {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResult(true)
        } catch (e: Exception) {
            AuthResult(false, e.message ?: "Кіру кезінде қате орын алды")
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    fun updateEmail(newEmail: String, onResult: (Boolean, String?) -> Unit) {
        val user = firebaseAuth.currentUser
        if (user != null) {
            user.updateEmail(newEmail)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true, null)
                    } else {
                        onResult(false, task.exception?.message)
                    }
                }
        } else {
            onResult(false, "Пайдаланушы табылмады")
        }
    }

    val readAllData = listItemsDao.getAllItems()

    suspend fun insert(item: ListItems) {
        listItemsDao.insert(item)
    }

    suspend fun delete(item: ListItems) {
        listItemsDao.delete(item)
    }
}
