package com.pe.coredatabase.di

import android.content.Context
import androidx.room.Room
import com.pe.coredatabase.AppDatabase
import com.pe.coredatabase.dao.DogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): AppDatabase =
        Room.databaseBuilder(appContext, AppDatabase::class.java, "dogs_db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideDogDao(db: AppDatabase): DogDao = db.dogDao()
}