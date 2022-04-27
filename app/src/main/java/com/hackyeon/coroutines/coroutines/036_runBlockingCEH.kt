package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import kotlin.random.Random

// 일반적으로 runBLocking은 ceh를 사용할수가 없다

suspend fun runBlockingPr1(): Int {
    delay(1000L)
    return Random.nextInt(0, 500)
}

suspend fun runBlockingPr2(): Int{
    delay(500L)
    throw ArithmeticException()
}

val runBlockingCeh = CoroutineExceptionHandler { _, exception ->
    println("something happend: $exception")
}

fun main() = runBlocking {
    val job = launch(ceh) {
        val a = async { runBlockingPr1() }
        val b = async { runBlockingPr2() }
        println(a.await())
        println(b.await())
    }
    job.join()
}