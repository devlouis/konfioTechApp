package com.pe.corenetwork

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error(val message: String, val code: Int? = null): NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    object NetworkError : NetworkResult<Nothing>()
    object Timeout : NetworkResult<Nothing>()
}