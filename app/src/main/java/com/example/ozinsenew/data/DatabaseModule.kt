package com.example.ozinsenew.data


import android.content.Context
import androidx.room.Room
import com.example.ozinsenew.data.room.bookmark.ListItemsDao
import com.example.ozinsenew.data.room.bookmark.ListItemsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ListItemsDatabase {
        return Room.databaseBuilder(
            context,
            ListItemsDatabase::class.java,
            "list_items_database"
        ).build()
    }

    @Provides
    fun provideListItemsDao(database: ListItemsDatabase): ListItemsDao {
        return database.listItemsDao()
    }
}
