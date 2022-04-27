package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

suspend fun noCancel() = coroutineScope {
    val job1 = launch {
        withContext(NonCancellable){
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
        delay(1000L)
        println("endPoint")
    }

    delay(800L)
    job1.cancel()
    println("2!")


}


fun main() = runBlocking {
    noCancel()
}