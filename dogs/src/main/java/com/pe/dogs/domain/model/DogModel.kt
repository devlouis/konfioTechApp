package com.pe.dogs.domain.model

data class DogModel(
    val id: String? = null,
    val dogName: String? = "",
    val description: String? = "",
    val age: Int? = 0,
    val url: String? = ""
)