package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select

fun CoroutineScope.sayFirst() = produce<String> {
    while (true) {
        delay(100)
        send("first")
    }
}

fun CoroutineScope.saySecond() = produce<String> {
    while (true) {
        delay(150)
        send("second")
    }
}

fun main() = runBlocking {
    val first = sayFirst()
    val second = saySecond()
    repeat(5) {
        select<Unit> {
            first.onReceive {
                println("first: $it")
            }
            second.onReceive {
                println("second: $it")
            }
        }
    }
    coroutineContext.cancelChildren()
}