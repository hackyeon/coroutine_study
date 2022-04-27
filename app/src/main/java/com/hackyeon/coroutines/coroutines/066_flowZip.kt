package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val nums = (1..3).asFlow().onEach { delay(100L) }
    val strs = flowOf("일", "이", "삼").onEach { delay(200L) }

    // combine 은 짝을 이루지 않고 데이터가 오는 대로 최신데이터(마지막)를 내보낸다
    nums.combine(strs) { a, b ->
        "${a}는 $b"
    }.collect { println(it) }

    // zip 은 양쪽의 데이터를 같은시점(array 로 치면 index) 로 짝을 지어 사용한다
//    nums.zip(strs) { a, b ->
//        "${a}는 $b"
//    }.collect { println(it) }


}