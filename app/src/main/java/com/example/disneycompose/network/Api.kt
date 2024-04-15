package com.example.disneycompose.network

import com.example.disneycompose.model.network.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/character")
    suspend fun getCharacter(): Response<CharacterResponse>
}