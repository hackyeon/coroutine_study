package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i first")
    delay(500L)
    emit("$i second")
}

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100L) }
        .flatMapLatest { // 처리되는도중 데이터가 들어오면 이전 데이터를 cancel한다
            requestFlow(it)
        }
//        .flatMapMerge { // requestFlow(1) 의 호출이 끝날떄까지 기다리는것이 아닌 바로바로 emit을 이어붙인다
//            requestFlow(it)
//        }
//        .flatMapConcat { // flow를 이어 붙인다 requestFLow(1) requestFlow(2) request(3)
//            requestFlow(it)
//        }
        .collect {
            println("$it at ${System.currentTimeMillis() - startTime} ms from start")
        }
}