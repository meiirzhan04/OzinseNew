package com.example.ozinsenew.room.bookmark

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItems(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val image: Int,
    val data: String,
    val category: String
)