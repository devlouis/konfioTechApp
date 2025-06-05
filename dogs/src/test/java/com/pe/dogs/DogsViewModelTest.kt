package com.pe.dogs

import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.usecase.DogsUseCase
import com.pe.dogs.presentation.state.UiState
import com.pe.dogs.presentation.viewmodel.DogViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DogViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @MockK
    lateinit var useCase: DogsUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `emite Loading y Success cuando getDogsList devuelve datos`() = runTest {
        // Arrange
        val fakeDogs = listOf(DogModel("1", "Firulais", "descr", 1, "https://url.com/foto"))
        coEvery { useCase.getDogsList() } returns Result.success(fakeDogs)

        val viewModel = DogViewModel(useCase).apply {
            testMode = true
        }

        val states = mutableListOf<UiState<List<DogModel>>>()

        val job = launch {
            viewModel.dogState
                .drop(1) // salta Idle
                .take(2) // Loading y Success
                .collect { states.add(it) }
        }


        viewModel.getDogsList()
        // Avanza el dispatcher para que se ejecuten las corutinas
        advanceUntilIdle()

        // Assert
        println("Estados recolectados: $states")
        assertEquals(2, states.size)
        assertTrue(states[0] is UiState.Loading)
        assertTrue(states[1] is UiState.Success)
        assertEquals(fakeDogs, (states[1] as UiState.Success).data)

        job.cancel()
    }

    @Test
    fun `emite Loading y Error cuando getDogsList falla`() = runTest {
        // Arrange
        val errorMessage = "Algo falló"
        coEvery { useCase.getDogsList() } returns Result.failure(Exception(errorMessage))

        val viewModel = DogViewModel(useCase).apply {
            testMode = true
        }

        val states = mutableListOf<UiState<List<DogModel>>>()
        val job = launch {
            viewModel.dogState
                .drop(1)
                .take(2)
                .collect { states.add(it) }
        }

        viewModel.getDogsList()
        advanceUntilIdle()

        // Assert
        assertEquals(2, states.size)
        assertTrue(states[0] is UiState.Loading)
        assertTrue(states[1] is UiState.Error)
        assertEquals(errorMessage, (states[1] as UiState.Error).message)

        job.cancel()
    }

    @Test
    fun `emite Loading y Error cuando getDogsList devuelve Timeout`() = runTest {
        // Arrange
        coEvery { useCase.getDogsList() } returns Result.failure(Exception("Tiempo de espera agotado."))
        val viewModel = DogViewModel(useCase).apply {
            testMode = true
        }

        val states = mutableListOf<UiState<List<DogModel>>>()
        val job = launch {
            viewModel.dogState
                .drop(1)
                .take(2)
                .collect { states.add(it) }
        }

        viewModel.getDogsList()
        advanceUntilIdle()

        // Assert
        assertEquals(2, states.size)
        assertTrue(states[0] is UiState.Loading)
        assertTrue(states[1] is UiState.Error)
        assertEquals("Tiempo de espera agotado.", (states[1] as UiState.Error).message)

        job.cancel()
    }
}