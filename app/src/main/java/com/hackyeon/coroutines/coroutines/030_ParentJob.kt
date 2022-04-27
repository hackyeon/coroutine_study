package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val job = launch {
        launch(Job()) { // 새로운 잡을 넣어 부모자시 관계를 끊는다
            println(coroutineContext[Job])
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }

        launch {
            println(coroutineContext[Job])
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
    }

    delay(500L)
    job.cancelAndJoin()
    delay(1000L)

}