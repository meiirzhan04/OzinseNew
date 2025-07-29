package com.example.ozinsenew.data.room.bookmark

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ListItems::class], version = 1)
abstract class ListItemsDatabase : RoomDatabase() {
    abstract fun listItemsDao(): ListItemsDao
    companion object {
        private var INSTANCE: ListItemsDatabase? = null
        fun getDatabase(context: Context): ListItemsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ListItemsDatabase::class.java,
                    "list_items_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}