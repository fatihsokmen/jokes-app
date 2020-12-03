package com.github.fatihsokmen.jokesapp.data

data class JokeDomain(
    val id: Int,
    val joke: String,
    val categories: List<String>
)