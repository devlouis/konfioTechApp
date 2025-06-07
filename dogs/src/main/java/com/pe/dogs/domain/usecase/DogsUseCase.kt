package com.pe.dogs.domain.usecase

import com.pe.corenetwork.NetworkResult
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.repository.DogsRepository
import javax.inject.Inject

class DogsUseCase @Inject constructor(
    private val  repository: DogsRepository
){
    suspend fun getDogsList(): Result<List<DogModel>> {
        return when (val result = repository.getDogs()) {
            is NetworkResult.Success -> Result.success(result.data)
            is NetworkResult.Error -> Result.failure(Exception(result.message))
            is NetworkResult.NetworkError -> Result.failure(Exception("Sin conexión a internet."))
            is NetworkResult.Timeout -> Result.failure(Exception("Tiempo de espera agotado."))
            else -> Result.failure(Exception("Error desconocido."))
        }
    }

    suspend fun clearLocalDogs(): Result<Unit> {
        return try {
            repository.clearForSync()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}