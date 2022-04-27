package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

suspend fun someCalc(i: Int): Int {
    delay(100L)
    return i * 2
}

fun main() = runBlocking<Unit> {
    (1..20).asFlow()
        .transform {
            emit(it)
            emit(someCalc(it))
        }
        .dropWhile { // 조건에 맞는동안 버리다가 한번이라도 조건에 벗어나는 순간부터 값을 모두 가져온다
            it < 15
        }
//        .drop(5) // 처음 몇개의 결과를 버린다
//        .takeWhile { // filter랑 차이점은 이건 조건에 안맞는순간 값을 가져오지않음
//            it < 15
//        }
//        .take(5) // 갯수
        .collect {
            println(it)
        }
}