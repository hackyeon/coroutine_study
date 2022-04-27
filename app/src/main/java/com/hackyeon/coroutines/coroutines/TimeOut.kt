package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

suspend fun doTimeOut() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 0
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10 && isActive) {
            val currentTime = System.currentTimeMillis()
            if(currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }

    job1.cancel()
}


fun main() = runBlocking {
    val result = withTimeoutOrNull(500L) {
        doTimeOut()
        true
    } ?: false

    println(result)

}