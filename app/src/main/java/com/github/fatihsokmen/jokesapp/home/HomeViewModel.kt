package com.github.fatihsokmen.jokesapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.github.fatihsokmen.jokesapp.core.coroutine.CoroutineDispatcherProvider
import com.github.fatihsokmen.jokesapp.core.livedata.LiveEvent
import com.github.fatihsokmen.jokesapp.core.livedata.invoke
import com.github.fatihsokmen.jokesapp.data.JokeDomain
import com.github.fatihsokmen.jokesapp.data.JokesInteractor
import com.github.fatihsokmen.jokesapp.data.toModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val dispatchers: CoroutineDispatcherProvider,
    private val interactor: JokesInteractor
) : ViewModel() {

    private val jokesDomain =
        MutableLiveData<List<JokeDomain>>()

    val jokesModels =
        jokesDomain.map { jokes ->
            jokes.map { it.toModel() }
        }

    val loading = MutableLiveData<Boolean>()

    val error = LiveEvent<String>()

    init {
        loadRandomJokes()
    }

    fun loadRandomJokes() {
        viewModelScope.launch {
            loading(true)
            withContext(dispatchers.io) {
                interactor.getJokes()
            }.onSuccess {
                jokesDomain.value = it
            }.onError {
                error(it.message.orEmpty())
            }
            loading(false)
        }
    }
}