package com.github.fatihsokmen.jokesapp.data


fun JokeDomain.toModel() =
    JokeModel(
        joke = joke,
        categories = categories.joinToString(" ") { "#$it" },
        showCategories = categories.isNotEmpty()
    )

fun JokeResponse.toDomain() =
    JokeDomain(
        id = id,
        joke = joke.orEmpty(),
        categories = categories
    )