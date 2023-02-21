package com.cblanco.rickandmortychars.features.characters.list.domain.model

import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListItemApiResponse

fun CharacterListItemApiResponse.toDomain() = CharacterItemModel(
    id = id ?: -1,
    gender = gender ?: "",
    image = image?: "",
    name = name?: "",
    species = species?: "",
    status = status?: "",
    type = type?: "",
    url = url?: "",
    locationName = location?.name ?: ""
)