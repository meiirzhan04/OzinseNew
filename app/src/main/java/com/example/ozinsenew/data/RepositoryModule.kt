package com.example.ozinsenew.data

import com.example.ozinsenew.data.room.bookmark.ListItemsDao
import com.example.ozinsenew.data.room.bookmark.ListRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideListRepository(
        firebaseAuth: FirebaseAuth,
        listItemsDao: ListItemsDao
    ): ListRepository {
        return ListRepository(firebaseAuth, listItemsDao)
    }
}
