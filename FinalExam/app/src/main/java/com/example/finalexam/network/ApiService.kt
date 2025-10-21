package com.example.finalexam.network

import retrofit2.Response
import retrofit2.http.GET

data class JokeResponse(
    val id: Int = 0,
    val type: String? = null,
    val setup: String? = null,
    val punchline: String? = null
)

interface ApiService {
    @GET("random_joke")
    suspend fun getRandomJoke(): Response<JokeResponse>

    @GET("jokes/random")
    suspend fun getRandomJokeAlt(): Response<JokeResponse>
}

