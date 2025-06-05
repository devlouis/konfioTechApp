package com.pe.dogs.data.repository

import com.pe.coredatabase.dao.DogDao
import com.pe.corenetwork.DogsApi
import com.pe.dogs.data.mapper.toDomain
import com.pe.dogs.data.mapper.toEntity
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.repository.DogsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepositoryImpl @Inject constructor(
    private val api: DogsApi,
    private val dao: DogDao
) : DogsRepository {

    override suspend fun getDogs(): List<DogModel> {
        val cachedDogs = dao.getAllDogs()
        return if (cachedDogs.isNotEmpty()){
            cachedDogs.map { it.toDomain()}
        } else {
            val remoteDogs = api.getDogs().map { it.toDomain() }
            dao.insertAll(remoteDogs.map { it.toEntity() })
            remoteDogs
        }
        //return api.getDogs().map { it.toDomain() }
    }
}