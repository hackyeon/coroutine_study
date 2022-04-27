package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.channels.*
import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
//    val channel = Channel<Int>(10) // 채널의 버퍼 갯수
    val channel = Channel<Int>(Channel.RENDEZVOUS) // 0
//    val channel = Channel<Int>(2, BufferOverflow.DROP_OLDEST)
    launch {
        for(i in 1..50) {
            println("${i} 전송중")
            channel.send(i) // 받든 안받는 채널로 계속 보낸다
        }
        channel.close()
    }

    for(i in channel) {
        println("${i} 수신")
        delay(100)
    }
    println("완료")

}