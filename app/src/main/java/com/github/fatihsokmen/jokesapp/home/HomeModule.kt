package com.github.fatihsokmen.jokesapp.home

import com.github.fatihsokmen.jokesapp.data.JokesApiService
import com.github.fatihsokmen.jokesapp.data.JokesInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val homeModule: Module = module {

    factory<JokesApiService> {
        get<Retrofit>().create(JokesApiService::class.java)
    }

    factory {
        JokesInteractor(apiService = get())
    }

    viewModel {
        HomeViewModel(
            dispatchers = get(),
            interactor = get()
        )
    }

}