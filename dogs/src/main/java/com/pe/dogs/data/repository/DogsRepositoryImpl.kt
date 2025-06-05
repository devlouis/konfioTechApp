package com.pe.dogs.data.repository

import com.pe.coredatabase.dao.DogDao
import com.pe.corenetwork.DogsApiService
import com.pe.corenetwork.NetworkResult
import com.pe.corenetwork.safeApiCall
import com.pe.dogs.data.mapper.toDomain
import com.pe.dogs.data.mapper.toEntity
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.repository.DogsRepository
import com.pe.utilities.logging.AppLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogsRepositoryImpl @Inject constructor(
    private val api: DogsApiService,
    private val dao: DogDao
) : DogsRepository {

    override suspend fun getDogs(): NetworkResult<List<DogModel>> {
        val cachedDogs = dao.getAllDogs()

        return if (cachedDogs.isNotEmpty()) {
            NetworkResult.Success(cachedDogs.map { it.toDomain() })
        } else {
            when (val result = safeApiCall { api.getDogs() }) {
                is NetworkResult.Success -> {
                    val dogs = result.data.map { it.toDomain() }
                    val entities = dogs.map { it.toEntity() }
                    entities.forEach {
                        AppLogger.v("DogEntity", "id: ${it.id}, name: ${it.dogName}")
                    }
                    dao.insertAll(dogs.map { it.toEntity() })
                    NetworkResult.Success(dogs)
                }
                is NetworkResult.Error -> NetworkResult.Error(result.message, result.code)
                is NetworkResult.NetworkError -> NetworkResult.NetworkError
                is NetworkResult.Timeout -> NetworkResult.Timeout
                else -> NetworkResult.Error("Error desconocido")
            }
        }
    }
}