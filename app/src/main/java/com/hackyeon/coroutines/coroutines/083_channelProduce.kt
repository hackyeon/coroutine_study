package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.*


fun main() = runBlocking<Unit> {
    val oneToTen = produce<Int> { // ProducerScope = CoroutineScope + SendChannel
        for(i in 1..10) {
            channel.send(i)
        }
    }
    // 아래 주석친 내용과 같다
//    val channel = Channel<Int>()
//    launch {
//        for(i in 1..10) {
//            channel.send(i)
//        }
//    }

    oneToTen.consumeEach {
        println(it)
    }
    println("완료")
}