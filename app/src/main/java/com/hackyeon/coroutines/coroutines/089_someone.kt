package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun someone(channel: Channel<String>, name: String) {
    for (comment in channel) {
        println("${name}: $comment")
        channel.send(comment.drop(1) + comment.first())
        delay(100)
    }
}

fun main() = runBlocking<Unit> {
    val channel = Channel<String>()
    launch {
        someone(channel, "민준")
    }
    launch {
        someone(channel, "서연")
    }
    channel.send("hack")
    delay(1000)
    coroutineContext.cancelChildren()
}