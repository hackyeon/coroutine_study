package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

suspend fun doThree() {
    println("doThree: ${Thread.currentThread().name}")
    delay(1000)
    println("3!")
}

suspend fun doOne() {
    println("doOne: ${Thread.currentThread().name}")
    println("1!")
}

suspend fun doTwo() {
    println("doTwo: ${Thread.currentThread().name}")
    delay(500)
    println("2!")
}
suspend fun doOneTwoThree() = coroutineScope { // this: 코루팀 // 부모 코루틴
    // launch 는 coroutines 안에서만 호출된다
    val job = launch { // this: 코루틴. Receiver. 수신객체 // 자식 코루틴
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L) // suspension point
        println("3!")
    }
    job.join() // suspension point

    launch { // this: 코루틴. Receiver. 수신객체 // 자식 코루틴
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }

    repeat(10000) {
        launch { // this: 코루틴. Receiver. 수신객체 // 자식 코루틴
//            println("launch3: ${Thread.currentThread().name}")
            delay(500L) // suspension point
//            println("2!")
        }
    }

    println("4!")
}

fun main() = runBlocking { // this: 코루틴. Receiver. 수신객체
    doOneTwoThree() // suspension point
    println("runBlocking: ${Thread.currentThread().name}")
    delay(100L) // suspension point
    println("5!")
//    doOneTwoThree() // Unit // suspension point
}
