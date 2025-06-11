package com.example.ozinsenew.data.home

import androidx.compose.foundation.layout.Box
import com.example.ozinsenew.R

data class BoxData(
    val image: Int,
    val title: String,
    val description: String
)

val headBoxData = listOf(
    BoxData(
        R.drawable.ic_page_1,
        title = "Қызғалдақтар мекені ",
        description = "Шытырман оқиғалы мультсериал Елбасының «Ұлы даланың жеті қыры» бағдарламасы аясында жүз..."
    ),
    BoxData(
        R.drawable.ic_page_2,
        title = "Ойыншықтар",
        description = "5 жасар Алуаның ойыншықтары өте көп. Ол барлығын бірдей жақсы көріп, ұқыпты, таза ұстайды"
    )
)

val middleBoxData = listOf(
    BoxData(
        R.drawable.ic_page_1,
        title = "Глобус",
        description = "2-бөлім"
    ),
    BoxData(
        R.drawable.ic_page_2,
        title = "Табиғат сақшылары",
        description = "4-бөлім"
    )
)

val boxData = listOf(
    BoxData(
        R.drawable.ic_page_1,
        title = "Айдар",
        description = "Мультсериал"
    ),
    BoxData(
        R.drawable.ic_page_2,
        title = "Суперкөлік Самұрық",
        description = "Мультсериал"
    ),
    BoxData(
            R.drawable.ic_page_1,
    title = "Айдар",
    description = "Мультсериал"
),
)