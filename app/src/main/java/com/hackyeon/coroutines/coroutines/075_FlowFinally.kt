package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException

fun finallySimple(): Flow<Int> = (1..3).asFlow()

fun main() = runBlocking<Unit> {
    finallySimple()
        .map {
            if(it > 2) {
                throw IllegalStateException()
            }
            it + 1
        }
        .catch { cause -> println("catch cause: $cause") }
        .onCompletion { cause -> println("onCompletion cause: $cause") } // cause 로 catch가 발생했는지 확인할수있다 null이면 예외가 발생하지않음
        .collect { println(it) }
//    try{
//        finallySimple().collect { println(it) }
//    }finally {
//        println("done")
//    }
}