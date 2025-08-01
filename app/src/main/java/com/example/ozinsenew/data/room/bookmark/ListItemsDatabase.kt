package com.example.ozinsenew.data.room.bookmark

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ListItems::class], version = 1)
abstract class ListItemsDatabase : RoomDatabase() {
    abstract fun listItemsDao(): ListItemsDao
}