package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking<Unit> {
    launch(Dispatchers.IO + CoroutineName("launch1")) {
        println("launch1: ${Thread.currentThread().name}")
//        println(coroutineContext[CoroutineDispatcher])
        println(coroutineContext[CoroutineName])
        delay(5000L)
    }

    launch(Dispatchers.Default + CoroutineName("launch2")) {
        println("launch2: ${Thread.currentThread().name}")
//        println(coroutineContext[CoroutineDispatcher])
        println(coroutineContext[CoroutineName])
        delay(10L)
    }

}