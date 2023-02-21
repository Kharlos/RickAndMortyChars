package com.cblanco.rickandmortychars.features.characters.list.domain.model


data class CharacterItemModel(
    val id: Int,
    val gender: String,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String,
    val locationName: String
):java.io.Serializable