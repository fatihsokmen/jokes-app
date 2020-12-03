package com.github.fatihsokmen.jokesapp.core.livedata

import androidx.lifecycle.MutableLiveData

inline operator fun <reified T> MutableLiveData<T>.invoke(value: T): Unit = setValue(value)

