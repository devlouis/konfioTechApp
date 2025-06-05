package com.pe.dogs.domain.usecase

import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.repository.DogsRepository
import javax.inject.Inject

class DogsUseCase @Inject constructor(
    private val  repository: DogsRepository
){
    suspend fun getDogsList(): List<DogModel> {
        return repository.getDogs()
    }
}