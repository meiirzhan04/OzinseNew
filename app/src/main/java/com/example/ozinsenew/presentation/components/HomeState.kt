package com.example.ozinsenew.presentation.components

import com.example.ozinsenew.domain.model.home.BoxData

data class HomeState(
    val headBoxData: List<BoxData> = emptyList(),
    val middleBoxData: List<BoxData> = emptyList(),
    val boxData: List<BoxData> = emptyList()
) {
    fun allBoxData() = headBoxData + middleBoxData + boxData
}
