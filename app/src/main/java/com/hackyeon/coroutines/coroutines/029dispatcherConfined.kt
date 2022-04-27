package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    async(Dispatchers.Unconfined) {
        println("Unconfined / ${Thread.currentThread().name}")
        delay(100L)
        println("Unconfined / ${Thread.currentThread().name}")
        delay(100L)
        println("Unconfined / ${Thread.currentThread().name}")
    }
}