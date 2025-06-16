package com.example.ozinsenew.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ozinsenew.R
import com.example.ozinsenew.data.home.BoxData
import com.google.firebase.auth.FirebaseAuth

class ViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var isAuthenticated by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>("Not")
        private set

    private val _headBoxData = listOf(
        BoxData(
            id = 0,
            R.drawable.ic_page_1,
            title = "Қызғалдақтар мекені ",
            description = "Шытырман оқиғалы мультсериал Елбасының «Ұлы даланың жеті қыры» бағдарламасы аясында жүзеге асырылған. Мақалада қызғалдақтардың отаны Қазақстан екені айтылады. Ал, жоба қызғалдақтардың отаны – Алатау баурайы екенін анимация тілінде дәлелдей түседі."
        ),
        BoxData(
            id = 1,
            R.drawable.ic_page_2,
            title = "Ойыншықтар",
            description = "5 жасар Алуаның ойыншықтары өте көп. Ол барлығын бірдей жақсы көріп, ұқыпты, таза ұстайды"
        )
    )

    private val _middleBoxData = listOf(
        BoxData(
            id = 2,
            R.drawable.ic_page_1,
            title = "Глобус",
            description = "2-бөлім"
        ),
        BoxData(
            id = 3,
            R.drawable.ic_page_2,
            title = "Табиғат сақшылары",
            description = "4-бөлім"
        )
    )

    private val _boxData = listOf(
        BoxData(
            id = 4,
            R.drawable.ic_page_1,
            title = "Айдар",
            description = "Мультсериал"
        ),
        BoxData(
            id = 5,
            R.drawable.ic_page_2,
            title = "Суперкөлік Самұрық",
            description = "Мультсериал"
        ),
        BoxData(
            id = 6,
            R.drawable.ic_page_1,
            title = "Айдар",
            description = "Мультсериал"
        ),
    )


    val headBoxData: List<BoxData> = _headBoxData
    val middleBoxData: List<BoxData> = _middleBoxData
    val boxData: List<BoxData> = _boxData

    fun getBoxById(id: Int): BoxData? {
        return (headBoxData + middleBoxData + boxData).find { it.id == id }
    }


    fun getMiddleBoxById(id: Int): BoxData? {
        return _middleBoxData.find { it.id == id }
    }

    fun getBoxDataById(id: Int): BoxData? {
        return _boxData.find { it.id == id }
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

    fun logout() {
        auth.signOut()
        isAuthenticated = false
    }
}
