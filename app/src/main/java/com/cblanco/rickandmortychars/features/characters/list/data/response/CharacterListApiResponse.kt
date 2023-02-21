package com.cblanco.rickandmortychars.features.characters.list.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterListApiResponse(
    @field:Json(name = "info")
    val info: CharacterListInfoApiResponse?,
    @field:Json(name = "results")
    val results: List<CharacterListItemApiResponse>
)

@JsonClass(generateAdapter = true)
data class CharacterListItemApiResponse(
    @field:Json(name = "id")
    val id: Int?,
    @field:Json(name = "created")
    val created: String?,
    @field:Json(name = "episode")
    val episode: List<String>?,
    @field:Json(name = "gender")
    val gender: String?,
    @field:Json(name = "image")
    val image: String?,
    @field:Json(name = "location")
    val location: CharacterListLocationApiResponse?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "origin")
    val origin: CharacterListOriginApiResponse?,
    @field:Json(name = "species")
    val species: String?,
    @field:Json(name = "status")
    val status: String?,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class CharacterListLocationApiResponse(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class CharacterListInfoApiResponse(
    @field:Json(name = "count")
    val count: Int?,
    @field:Json(name = "next")
    val next: String?,
    @field:Json(name = "pages")
    val pages: Int?,
    @field:Json(name = "prev")
    val prev: Any?
)

@JsonClass(generateAdapter = true)
data class CharacterListOriginApiResponse(
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "url")
    val url: String?
)