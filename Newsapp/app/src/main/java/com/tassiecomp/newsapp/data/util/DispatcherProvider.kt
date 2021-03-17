package com.tassiecomp.newsapp.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}