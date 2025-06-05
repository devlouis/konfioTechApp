package com.pe.dogs.presentation.state

import com.pe.dogs.domain.model.DogModel

sealed class DogState {
    object Idle: DogState()
    data object Loading : DogState()
    data class Success(val data: List<DogModel>) : DogState()
    data class Error(val message: String) : DogState()
}