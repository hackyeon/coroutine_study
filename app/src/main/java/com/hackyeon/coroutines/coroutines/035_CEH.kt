package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun pr1() {
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun pr2(){
    delay(500L)
    throw ArithmeticException()
}

val ceh = CoroutineExceptionHandler { _, exception ->
    println("something happend: $exception")
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch(ceh) {
        launch { pr1() }
        launch { pr2() }
    }
    job.join()
}