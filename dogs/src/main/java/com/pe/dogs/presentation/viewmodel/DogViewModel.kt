package com.pe.dogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pe.dogs.R
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.usecase.DogsUseCase
import com.pe.dogs.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _dogBDState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val dogBDState: StateFlow<UiState<Unit>> = _dogBDState

    private val _dogBDEvent = MutableSharedFlow<String>() // Para eventos como snackbar
    val dogBDEvent = _dogBDEvent.asSharedFlow()

    fun getDogsList() {
        viewModelScope.launch {
            _dogState.value = UiState.Loading
            if (testMode) kotlinx.coroutines.yield()
            val result = dogsUseCase.getDogsList()
            _dogState.value = result.fold(
                onSuccess = { UiState.Success(it) },
                onFailure = { UiState.Error(it.message ?: R.string.unknown_error.toString()) }
            )
        }
    }

    fun clearDogs() {
        viewModelScope.launch {
            _dogBDState.value = UiState.Loading
            try {
                dogsUseCase.clearLocalDogs()
                _dogBDEvent.emit(R.string.dogs_list_clear.toString())
            } catch (e: Exception) {
                _dogBDEvent.emit(R.string.dogs_list_clear_error.toString())
            }

        }
    }
}