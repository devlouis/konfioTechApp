package com.pe.corenetwork

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                NetworkResult.Success(it)
            } ?: NetworkResult.Error("Cuerpo vacío", response.code())
        } else {
            NetworkResult.Error("HTTP ${response.code()}: ${response.message()}", response.code())
        }
    } catch (e: SocketTimeoutException) {
        NetworkResult.Timeout
    } catch (e: UnknownHostException) {
        NetworkResult.NetworkError
    } catch (e: IOException) {
        NetworkResult.Error("No se pudo conectar al servidor. Verifica tu conexión a internet.")
    } catch (e: HttpException) {
        NetworkResult.Error("Error del servidor: ${e.code()} ${e.message()}")
    } catch (e: Exception) {
        NetworkResult.Error(e.localizedMessage ?: "Unknown error")
    }
}