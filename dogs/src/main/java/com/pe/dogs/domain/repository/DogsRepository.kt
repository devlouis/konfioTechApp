package com.pe.dogs.domain.repository

import com.pe.corenetwork.NetworkResult
import com.pe.dogs.domain.model.DogModel

interface DogsRepository {
    suspend fun getDogs(): NetworkResult<List<DogModel>>
    suspend fun clearForSync(): Result<Unit>
}