package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.reduce

suspend fun operationSomeCalc(i: Int): Int{
    delay(10L)
    return i * 2
}

fun main() = runBlocking<Unit> {
    val value = (1..10)
        .asFlow()
            // 종단 연산자, terminal operator 특정감, 컬렉션, 결과를 리턴한다
        .count { // 조건에 맞는 데이터의 갯수를 리턴한다
            it % 2 == 0
        }
//        .fold(10) { a, b -> // reduce와 달리 초기값을 갖는다
//            a + b
//        }
//        .reduce { a, b ->
//            a + b
//        }
        
    println(value)
}