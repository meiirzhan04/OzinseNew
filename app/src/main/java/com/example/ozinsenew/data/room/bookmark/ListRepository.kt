package com.example.ozinsenew.data.room.bookmark

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class AuthResult(
    val successful: Boolean,
    val message: String = ""
)

class ListRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val listItemsDao: ListItemsDao
) {

    suspend fun insert(item: ListItems) = listItemsDao.insert(item)

    suspend fun isBookmarked(item: ListItems): Boolean =
        listItemsDao.isBookmarked(item.name, item.data, item.image, item.category)

    suspend fun deleteByFields(item: ListItems) =
        listItemsDao.deleteByFields(item.name, item.data, item.image, item.category)

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

}
