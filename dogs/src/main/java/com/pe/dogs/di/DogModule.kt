package com.pe.dogs.di

import android.app.Application
import android.content.Context
import com.pe.coredatabase.dao.DogDao
import com.pe.corenetwork.DogsApiService
import com.pe.dogs.data.repository.DogsRepositoryImpl
import com.pe.dogs.domain.repository.DogsRepository
import com.pe.dogs.domain.usecase.DogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DogModule {
    @Provides
    fun provideApplicationContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideDogRepository(
        api: DogsApiService,
        dao: DogDao
    ): DogsRepository = DogsRepositoryImpl(api, dao)

    @Provides
    fun provideDogsUseCase(
        repository: DogsRepository
    ): DogsUseCase = DogsUseCase(repository)
}