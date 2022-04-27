package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun printRan() {
    delay(500L)
    println(Random.nextInt(0, 500))
}

fun main() {
    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("scope"))
    val job = scope.launch(Dispatchers.IO) {
        printRan()
    }
    Thread.sleep(1000L)
}