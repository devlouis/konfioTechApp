package com.pe.dogs.domain.repository

import com.pe.dogs.domain.model.DogModel

interface DogsRepository {
    suspend fun getDogs():  List<DogModel>
}