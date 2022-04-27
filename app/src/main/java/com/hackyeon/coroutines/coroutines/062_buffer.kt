package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun bufferSimple(): Flow<Int> = flow {
    for(i in 1..3) {
        delay(100L)
        emit(i)
    }
}


fun main() = runBlocking {
    val time = measureTimeMillis {
        bufferSimple()
            .collectLatest { // 처리하는 도중 받은값들의 마지막값만 처리한다
                println("값 $it 처리 시작")
                delay(300L)
                println(it)
                println("처리 완료")
            }
//            .conflate() // 처리하고있는동안의 받은 데이터를 버린다
//            .buffer() // collect 의 준비상태와 관계없이 데이터를 흘러보낸다
//            .collect {
//                delay(300L)
//                println("it: $it")
//            }
    }
    println("time: $time")
}