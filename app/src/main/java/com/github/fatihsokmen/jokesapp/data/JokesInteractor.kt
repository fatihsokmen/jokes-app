package com.github.fatihsokmen.jokesapp.data

import java.lang.Exception
import java.lang.IllegalArgumentException

class JokesInteractor(
    private val apiService: JokesApiService
) {

    suspend fun getJokes() = try {
        val jokesResponse = apiService.getRandomJokes(numberOfJokes = JOKES_COUNT)

        val jokes = if (jokesResponse.type == SUCCESS) {
            jokesResponse.jokes?.map { it.toDomain() } ?: emptyList()
        } else {
            throw IllegalArgumentException("Unexpected response type")
        }
        Result.Success(jokes)
    } catch (e: Exception) {
        Result.Error(e)
    }

    companion object {
        const val JOKES_COUNT = 100
    }
}