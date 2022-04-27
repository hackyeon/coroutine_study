package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        for(x in 1..10) {
            channel.send(x) // suspension point
        }
        channel.close() // close 한경우 channel 을 for문으로 돌릴수있다
    }
//    launch {
//        repeat(10) {
//            // 코루틴이 잠들기때문에 같은 코루틴에서 사용하는것은 위험하다
//            println(channel.receive()) // suspension point
//        }
//        println("완료")
//    }

    for(i in channel) { // close를 하지않은경우 for문이 언제 끝날지 모르기때문에 for문을 돌릴경우 반드시 닫아줘야함
        println(i)
    }
    println("완료")

}