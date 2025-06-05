package com.pe.dogs

import com.pe.corenetwork.NetworkResult
import com.pe.dogs.domain.model.DogModel
import com.pe.dogs.domain.repository.DogsRepository
import com.pe.dogs.domain.usecase.DogsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DogsUseCaseTest {

    private lateinit var repository: DogsRepository
    private lateinit var useCase: DogsUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = DogsUseCase(repository)
    }

    @Test
    fun `getDogsList devuelve Success cuando repository devuelve Success`() = runTest {
        val dogsList = listOf(DogModel("id1", "Rex", "Desc", 5, "url"))

        coEvery { repository.getDogs() } returns NetworkResult.Success(dogsList)

        val result = useCase.getDogsList()

        assertTrue(result.isSuccess)
        assertEquals(dogsList, result.getOrNull())
    }

    @Test
    fun `getDogsList devuelve failure con mensaje de error cuando repository devuelve Error`() = runTest {
        val errorMsg = "Error del servidor"
        coEvery { repository.getDogs() } returns NetworkResult.Error(errorMsg, 500)

        val result = useCase.getDogsList()

        assertTrue(result.isFailure)
        assertEquals(errorMsg, result.exceptionOrNull()?.message)
    }

    @Test
    fun `getDogsList devuelve failure con mensaje de network error`() = runTest {
        coEvery { repository.getDogs() } returns NetworkResult.NetworkError

        val result = useCase.getDogsList()

        assertTrue(result.isFailure)
        assertEquals("Sin conexión a internet.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getDogsList devuelve failure con mensaje de timeout`() = runTest {
        coEvery { repository.getDogs() } returns NetworkResult.Timeout

        val result = useCase.getDogsList()

        assertTrue(result.isFailure)
        assertEquals("Tiempo de espera agotado.", result.exceptionOrNull()?.message)
    }

    @Test
    fun `getDogsList devuelve failure con mensaje desconocido para casos no contemplados`() = runTest {
        coEvery { repository.getDogs() } returns NetworkResult.Loading

        val result = useCase.getDogsList()

        assertTrue(result.isFailure)
        assertEquals("Error desconocido.", result.exceptionOrNull()?.message)
    }
}