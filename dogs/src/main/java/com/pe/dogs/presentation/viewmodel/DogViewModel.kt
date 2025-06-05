package com.pe.dogs.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pe.corenetwork.model.DogDto
import com.pe.dogs.domain.usecase.DogsUseCase
import com.pe.dogs.presentation.state.DogState
import com.pe.utilities.logging.AppLogger
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

    private val _dogState = MutableStateFlow<DogState>(DogState.Idle)
    val dogState: StateFlow<DogState> = _dogState

    init {
        getDogsList()
    }

    fun getDogsList(){
        viewModelScope.launch {
            _dogState.value = DogState.Loading
            try {
                val dogs = dogsUseCase.getDogsList()
                if (dogs.isNotEmpty()){
                    AppLogger.v(tag = TAG, "$dogs")
                    _dogState.value = DogState.Success(dogs)
                } else {
                    AppLogger.v(tag = TAG, "Error")
                    _dogState.value = DogState.Error("Error")
                }
            } catch (e: Exception) {
                AppLogger.v(tag = TAG,"Exception Error: ${e.message}")
                _dogState.value = DogState.Error("Exception Error: ${e.message}")
            }
        }
    }

}