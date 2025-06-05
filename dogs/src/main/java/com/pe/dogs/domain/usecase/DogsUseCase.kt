package com.pe.dogs.domain.usecase

import com.pe.corenetwork.NetworkResult
import com.pe.dogs.domain.Resource
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.repository.DogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsUseCase @Inject constructor(
    private val  repository: DogsRepository
){
    /*fun getDogs(): Flow<Resource<List<DogModel>>> = flow {
        emit(Resource.Loading)

        try {
            val dogs = repository.getDogs()
            emit(Resource.Success(dogs))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Error desconocido"))
        }
    }*/
    suspend fun getDogsList(): NetworkResult<List<DogModel>> = repository.getDogs()

   /* suspend fun getDogsList(): Result<List<DogModel>> {
        return when (val result = repository.getDogs()) {
            is NetworkResult.Success -> Result.success(result.data)
            is NetworkResult.Error -> Result.failure(Exception(result.message))
            is NetworkResult.Timeout -> Result.failure(Exception("Timeout"))
            is NetworkResult.NetworkError -> Result.failure(Exception("Sin conexión"))
            else -> Result.failure(Exception("Error desconocido"))
        }
    }*/
}