package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach

suspend fun produceNum2(channel: SendChannel<Int>, from: Int, interval: Long) {
    var x = from
    while (true) {
        channel.send(x)
        x += 2
        delay(interval)
    }
}

fun CoroutineScope.processNum2(channel: ReceiveChannel<Int>) = launch {
    channel.consumeEach {
        println("${it}을 받았습니다.")
    }
}

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch {
        produceNum2(channel, 1, 100L)
    }
    launch {
        produceNum2(channel, 2, 150L)
    }
    processNum2(channel)
    delay(1000)
    coroutineContext.cancelChildren()
}