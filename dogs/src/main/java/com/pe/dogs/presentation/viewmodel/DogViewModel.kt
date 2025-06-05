package com.pe.dogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.usecase.DogsUseCase
import com.pe.dogs.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val dogsUseCase: DogsUseCase
): ViewModel() {
    val TAG = "DogViewModel"

    // Flag solo visible para testing
    var testMode: Boolean = false

    private val _dogState = MutableStateFlow<UiState<List<DogModel>>>(UiState.Idle)
    val dogState: StateFlow<UiState<List<DogModel>>> = _dogState

    fun getDogsList() {
        viewModelScope.launch {
            _dogState.value = UiState.Loading
            if (testMode) kotlinx.coroutines.yield()
            val result = dogsUseCase.getDogsList()
            _dogState.value = result.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: "Error desconocido") }
            )
        }
    }
}