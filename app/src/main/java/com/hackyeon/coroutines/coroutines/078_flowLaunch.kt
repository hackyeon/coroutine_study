package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// 이벤트 FLow 처리

fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun launchLog(msg: String) = println("${Thread.currentThread().name}: $msg")

fun main() = runBlocking<Unit> { // this: 코루틴 스코프, 코루틴
    events()
        .onEach { event -> launchLog("Event: $event") }
//        .collect() // 스트림이 끝날 때까지 기다린다, 이벤트 -> 계속 발생
        .launchIn(this) // 코루틴 스코프 // 새로운 코루틴

    launchLog("Done")
}