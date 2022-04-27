package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*


suspend fun doNumbers() = coroutineScope {
    val job1 = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }

    val job2 = launch {
        println("launch2: ${Thread.currentThread().name}")
        delay(1500L)
        println("1!")
    }

    val job3 = launch {
        println("launch3: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }

    delay(300L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

fun main() = runBlocking {
    doNumbers()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}

