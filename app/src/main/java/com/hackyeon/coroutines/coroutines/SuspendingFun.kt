package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt
import kotlin.system.measureTimeMillis

suspend fun getRandom1(): Int {
    try {
        println("start ran1")
        delay(1000L)
//    println("getRan1 ${Thread.currentThread().name}")
        return Random.nextInt(0, 500)
    }finally {
        println("getRandom1 cancel")
    }

}

suspend fun getRandom2(): Int {
        delay(500L)
//    println("getRan2 ${Thread.currentThread().name}")
//        return Random.nextInt(0, 500)
    throw IllegalStateException()
}

suspend fun doSomething() = coroutineScope { // 부모 코루틴
    val v1 = async {  // 자식 코루틴
        getRandom1()
    }
    val v2 = async { // 자식 코루틴 // 문제발생
        getRandom2()
    }

    try {
        println("${v1.await()} + ${v2.await()} = ${v1.await() + v2.await()}")
    }finally {
        println("doSomething is cancel")
    }

}



fun main() = runBlocking {
    try {
        doSomething()
    } catch (e: Exception) {
        println("doSomething failed: $e")
    }


//    val time = measureTimeMillis {
//        // lazy 를 자주 쓰진 않지만 async와 같이 쓰일수 있다 정도만 알고 넘어가자
//        val num1 = async(start = CoroutineStart.LAZY) {
//            getRandom1()
//        }
//        val num2 = async(start = CoroutineStart.LAZY) {
//            getRandom2()
//        }
//
//        num1.start()
//        num2.start()
//
////        println("${num1} + ${num2} = ${num1 + num2}")
//        println("${num1.await()} + ${num2.await()} = ${num1.await() + num2.await()}")
//
//    }
//    println("time: $time")
}