package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

// GlobalScope 전역 코루틴
// 계층관리가 어려워서 권장하지않음

suspend fun printRandom(){
    delay(500L)
    println(Random.nextInt(0, 500))
}

fun main() {
    val job = GlobalScope.launch(Dispatchers.IO) {
        launch { printRandom() }
    }
    Thread.sleep(1000L)
}