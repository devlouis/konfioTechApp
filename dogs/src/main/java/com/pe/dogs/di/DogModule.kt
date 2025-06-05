package com.pe.dogs.di

import android.app.Application
import android.content.Context
import com.pe.corenetwork.DogsApi
import com.pe.dogs.data.repository.DogsRepositoryImpl
import com.pe.dogs.domain.repository.DogsRepository
import com.pe.dogs.domain.usecase.DogsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        api: DogsApi
     /*   dao: DogDao*/
    ): DogsRepository = DogsRepositoryImpl(api)

    @Provides
    fun provideDogsUseCase(
        repository: DogsRepository
    ): DogsUseCase = DogsUseCase(repository)
}