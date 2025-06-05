package com.pe.corenetwork

import com.pe.corenetwork.model.DogDto
import retrofit2.http.GET

interface DogsApi {
    @GET("api/1151549092634943488")
    suspend fun getDogs(): List<DogDto>
}