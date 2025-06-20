package com.example.ozinsenew.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.ozinsenew.R
import com.example.ozinsenew.data.home.BoxData
import com.example.ozinsenew.room.bookmark.ListItems
import com.example.ozinsenew.room.bookmark.ListItemsDatabase
import com.example.ozinsenew.room.bookmark.ListRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow

@Suppress("DEPRECATION")
class ViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: Flow<List<ListItems>>
    private val repository: ListRepository
    private val auth = FirebaseAuth.getInstance()

    init {
        val listItemsDao = ListItemsDatabase.getDatabase(application).listItemsDao()
        repository = ListRepository(listItemsDao)
        readAllData = repository.readAllData
    }

    var isAuthenticated by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>("Not")
        private set


    private val _headBoxData = listOf(
        BoxData(
            id = 0,
            R.drawable.ic_page_1,
            title = "Қызғалдақтар мекені ",
            description = "Шытырман оқиғалы мультсериал Елбасының «Ұлы даланың жеті қыры» бағдарламасы аясында жүзеге асырылған. Мақалада қызғалдақтардың отаны Қазақстан екені айтылады. Ал, жоба қызғалдақтардың отаны – Алатау баурайы екенін анимация тілінде дәлелдей түседі.",
            category = "head"
        ),
        BoxData(
            id = 1,
            R.drawable.ic_page_2,
            title = "Ойыншықтар",
            description = "5 жасар Алуаның ойыншықтары өте көп. Ол барлығын бірдей жақсы көріп, ұқыпты, таза ұстайды",
            category = "head"
        )
    )

    private val _middleBoxData = listOf(
        BoxData(
            id = 2,
            R.drawable.image_globys,
            title = "Глобус",
            description = "2-бөлім",
            category = "middle"
        ),
        BoxData(
            id = 3,
            R.drawable.image_natural,
            title = "Табиғат сақшылары",
            description = "4-бөлім",
            category = "middle"
        )
    )

    private val _boxData = listOf(
        BoxData(
            id = 4,
            R.drawable.image_aidar,
            title = "Айдар",
            description = "Мультсериал",
            category = "box"
        ),
        BoxData(
            id = 5,
            R.drawable.image_car,
            title = "Суперкөлік Самұрық",
            description = "Мультсериал",
            category = "box"
        ),
        BoxData(
            id = 6,
            R.drawable.image_cinema,
            title = "Каникулы off-line 2",
            description = "Телехикая",
            category = "box"
        ),
    )


    val headBoxData: List<BoxData> = _headBoxData
    val middleBoxData: List<BoxData> = _middleBoxData
    val boxData: List<BoxData> = _boxData

    fun getBoxById(id: Int): BoxData? {
        return (headBoxData + middleBoxData + boxData).find { it.id == id }
    }

    fun allBoxData(): List<BoxData> {
        return headBoxData + middleBoxData + boxData
    }

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

    fun updateFirebaseEmail(newEmail: String, onResult: (Boolean, String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
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
            onResult(false, "User is null")
        }
    }

    fun logout() {
        auth.signOut()
        isAuthenticated = false
    }

}
