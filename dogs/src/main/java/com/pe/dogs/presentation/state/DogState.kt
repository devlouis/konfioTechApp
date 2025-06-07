package com.pe.dogs.presentation.state

import com.pe.dogs.domain.model.DogModel

sealed class DogState {
    object Idle : DogState()
    object Loading : DogState()
    data class Success(val dogs: List<DogModel>) : DogState()
    data class Error(val message: String) : DogState()
}