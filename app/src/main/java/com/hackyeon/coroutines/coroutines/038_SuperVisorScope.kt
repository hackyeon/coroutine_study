package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun superScopePrintRan1(){
    delay(1000L)
    println(Random.nextInt(0, 500))
}

suspend fun superScopePrintRan2() {
    delay(500L)
    throw ArithmeticException()
}

suspend fun supervisorFunc() = supervisorScope {
    launch { superScopePrintRan1() }
    launch(superScopeCeh) { superScopePrintRan2() }
}

val superScopeCeh = CoroutineExceptionHandler { _, exception ->
    println("something happend: $exception")
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch {
        supervisorFunc()
    }
    job.join()
}