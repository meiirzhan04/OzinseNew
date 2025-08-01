package com.example.ozinsenew.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.ozinsenew.R
import com.example.ozinsenew.domain.model.home.BoxData
import com.example.ozinsenew.presentation.components.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        _state.value = HomeState(
            headBoxData = listOf(
                BoxData(0, R.drawable.ic_page_1, "Қызғалдақтар мекені", "Мақалада...", "head"),
                BoxData(1, R.drawable.ic_page_2, "Ойыншықтар", "5 жасар Алуаның...", "head")
            ),
            middleBoxData = listOf(
                BoxData(2, R.drawable.image_globys, "Глобус", "2-бөлім", "middle"),
                BoxData(3, R.drawable.image_natural, "Табиғат сақшылары", "4-бөлім", "middle")
            ),
            boxData = listOf(
                BoxData(4, R.drawable.image_aidar, "Айдар", "Мультсериал", "box"),
                BoxData(5, R.drawable.image_car, "Суперкөлік Самұрық", "Мультсериал", "box"),
                BoxData(6, R.drawable.image_cinema, "Каникулы off-line 2", "Телехикая", "box")
            )
        )
    }

    fun getBoxById(id: Int): BoxData? {
        return (_state.value.headBoxData + _state.value.middleBoxData + _state.value.boxData)
            .find { it.id == id }
    }
}
