package com.github.fatihsokmen.jokesapp.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.flextrade.jfixture.JFixture
import com.github.fatihsokmen.jokesapp.UnitTestCoroutineDispatcherProvider
import com.github.fatihsokmen.jokesapp.data.JokesInteractor
import com.github.fatihsokmen.jokesapp.data.Result
import com.github.fatihsokmen.jokesapp.getOrAwaitValue
import com.github.fatihsokmen.jokesapp.invoke
import io.kotlintest.shouldBe
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import java.lang.IllegalArgumentException

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get: Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var sut: HomeViewModel
    private lateinit var interactor: JokesInteractor

    private val dispatchers = UnitTestCoroutineDispatcherProvider()
    private val fixture = JFixture()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatchers.main)
        interactor = mockk(relaxed = true)
        sut = HomeViewModel(
            dispatchers, interactor
        )
    }

    @After
    fun reset() {
        Dispatchers.resetMain()
    }

    @Test
    fun `view model should load data when first created`() {
        coEvery {
            interactor.getJokes()
        } returns Result.Success(fixture())

        coVerify {
            interactor.getJokes()
        }
    }

    @Test
    fun `error call should show error`() {
        val error = IllegalArgumentException(fixture<String>())
        coEvery {
            interactor.getJokes()
        } returns Result.Error(error)

        sut.loadRandomJokes()

        sut.error.getOrAwaitValue() shouldBe error.message
    }
}