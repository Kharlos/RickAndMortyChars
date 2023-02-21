package com.cblanco.rickandmortychars.core.data.api

import com.cblanco.rickandmortychars.features.characters.list.data.response.CharacterListApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface APIService {

    companion object {
        //Headers
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val ACCEPT = "Accept: application/json"
    }

    @Headers(CONTENT_TYPE, ACCEPT)
    @GET("character")
    suspend fun getCharacters(): Response<CharacterListApiResponse>
}