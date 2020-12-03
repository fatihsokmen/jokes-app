package com.github.fatihsokmen.jokesapp.data

import com.flextrade.jfixture.JFixture
import com.github.fatihsokmen.jokesapp.invoke
import io.kotlintest.shouldBe
import org.junit.Test

class JokeMapperKtTest {

    private val fixture = JFixture()

    @Test
    fun `domain to model mapping`() {
        val domain = fixture<JokeDomain>().copy(categories = listOf("a", "b"))

        val actual = domain.toModel()

        actual.joke shouldBe domain.joke
        actual.showCategories shouldBe true
        actual.categories shouldBe "#a #b"
    }

    @Test
    fun `domain to model mapping with empty categories`() {
        val domain = fixture<JokeDomain>().copy(categories = emptyList())

        val actual = domain.toModel()

        actual.joke shouldBe domain.joke
        actual.showCategories shouldBe false
        actual.categories shouldBe ""
    }

    @Test
    fun `request to domain mapping when success`() {
        val response = fixture<JokeResponse>()

        val actual = response.toDomain()

        actual.id shouldBe response.id
        actual.joke shouldBe response.joke
        actual.categories shouldBe response.categories
    }
}