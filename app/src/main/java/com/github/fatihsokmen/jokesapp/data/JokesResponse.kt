package com.github.fatihsokmen.jokesapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

internal const val SUCCESS = "success"

@JsonClass(generateAdapter = true)
data class JokesResponse(
    @Json(name = "type") val type: String,
    @Json(name = "value") val jokes: List<JokeResponse>?
)

@JsonClass(generateAdapter = true)
data class JokeResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "joke") val joke: String?,
    @Json(name = "categories") val categories: List<String>
)