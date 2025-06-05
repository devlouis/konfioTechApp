package com.pe.corenetwork.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogDto(
    @Json(name = "dogName") val dogName: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "age") val age: Int?,
    @Json(name = "image") val url: String?
)