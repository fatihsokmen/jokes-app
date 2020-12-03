package com.github.fatihsokmen.jokesapp.data

import com.flextrade.jfixture.JFixture
import com.github.fatihsokmen.jokesapp.invoke
import io.kotlintest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class JokesInteractorTest {

    private lateinit var apiService: JokesApiService

    private lateinit var sut: JokesInteractor

    private val fixture = JFixture()

    @Before
    fun setUp() {
        apiService = mockk()
        sut = JokesInteractor(apiService)
    }

    @Test
    fun `interactor should call service to load jokes`() = runBlockingTest {
        sut.getJokes()

        coVerify {
            apiService.getRandomJokes(JokesInteractor.JOKES_COUNT)
        }
    }

    @Test
    fun `failed call should return error`() = runBlockingTest {
        coEvery { apiService.getRandomJokes(any()) } throws HttpException(
            mockk<Response<Any>>(
                relaxed = true
            )
        )

        val actual = sut.getJokes()

        actual.shouldBeTypeOf<Result.Error<Any>>()
    }

    @Test
    fun `successful call should return success`() = runBlockingTest {
        val response = fixture<JokesResponse>().copy(type = SUCCESS)
        coEvery { apiService.getRandomJokes(any()) } returns response

        val actual = sut.getJokes()

        actual.shouldBeTypeOf<Result.Success<Any>>()
    }

    @Test
    fun `unknown response type should return error`() = runBlockingTest {
        val response = fixture<JokesResponse>().copy(type = fixture())
        coEvery { apiService.getRandomJokes(any()) } returns response

        val actual = sut.getJokes()

        actual.shouldBeTypeOf<Result.Error<Any>>()
    }
}