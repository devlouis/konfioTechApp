package com.pe.dogs.data.mapper

import com.pe.coredatabase.entity.DogEntity
import com.pe.corenetwork.model.DogDto
import com.pe.dogs.domain.model.DogModel

// DogDto -> Domain
fun DogDto.toDomain(): DogModel {
    return DogModel(
        dogName = dogName,
        description = description,
        age = age,
        url = url
    )
}

// DogEntity -> Domain
fun DogEntity.toDomain(): DogModel {
    return DogModel(
        id = id,
        dogName = dogName,
        description = description,
        age = age,
        url = imageUrl
    )
}

// Domain -> DogEntity (para guardar en BD)
fun DogModel.toEntity(): DogEntity {
    return DogEntity(
        id = id ?: "",
        dogName = dogName ?: "",
        description = description ?: "",
        age = age ?: 0,
        imageUrl = url?: ""
    )
}
