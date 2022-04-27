package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

// flow에서는 catch연사자를 이용하여 예외처리하는것을 권장한다

//fun trySimple(): Flow<Int> = flow {
fun trySimple(): Flow<String> = flow {
    for(i in 1..3) {
        println("emit $i")
        emit(i)
    }
}.map {
    check(it <= 1) {"crashed $it"} // flow를 만드는 과정에서 예외처리 예외투명성을 어기는것
    "string $it"
//    it
}

fun main() = runBlocking<Unit> {
    trySimple()
//        .catch { println("catch") }
        .catch { e -> emit("catch ${e}") } // 근데 왜 에러코드를 emit하는거지?? // 여기서 새로운 데이터로 바꿔서 emit 하거나 다시 예외를 던지기 위해
        // catch 기준 업스트림의 예외처리만 가능하고 다운스트림에 영향을 미치지 않는다
        .collect {
//            check( false ){ "test" } // 여기에는 영향을 미치지 못한다
            println(it)
        }

//    try {
//        trySimple().collect {
//            println(it)
////            check(it <= 1) {"Collect $it"} // 수집기에서 예외처리
//        }
//
//    } catch (e: Throwable) {
//        println("catch $e")
//    }
}