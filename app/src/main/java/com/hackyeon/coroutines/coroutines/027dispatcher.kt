package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    launch {
        println("부모 Context / ${Thread.currentThread().name}")
    }

    launch(Dispatchers.Default) {
        println("Default / ${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
        println("IO / ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        println("Unconfined / ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("hack")) {
        println("newSingleThreadContext / ${Thread.currentThread().name}")
    }

}