package com.github.fatihsokmen.jokesapp.data

import retrofit2.http.GET
import retrofit2.http.Path


interface JokesApiService {

    @GET("jokes/random/{numberOfJokes}?exclude=[explicit]")
    suspend fun getRandomJokes(@Path("numberOfJokes") numberOfJokes: Int): JokesResponse
}