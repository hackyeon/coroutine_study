package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

fun flowSomething(): Flow<Int> = flow<Int> {
    repeat(10) {
        // 이게 rx의 just 같음
        emit(Random.nextInt(0, 500))
        delay(100L)
    }
}

fun main() = runBlocking<Unit> {
    flowSomething()
        // 그냥 rx랑 똑같넹
        .filterNot {
            it % 2 == 0
        }
        .filter { // 중간 연산자 결과값이 없다 collect 를 통해 데이터를 사용한다
            it % 2 == 0 // 술어 predicate
        }
        .map {
            "$it $it"
        }
        .collect { // rx의 subscribe
            println(it)
        }
//    val result = withTimeoutOrNull(500L) {
//        flowSomething().collect {
//            println(it)
//        }
//        true
//    } ?: false
//
//    println("result: $result")

//    flowSomething().collect { value ->
//        println(value)
//    }
}