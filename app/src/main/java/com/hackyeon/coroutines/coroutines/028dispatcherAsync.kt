package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*

fun main() = runBlocking<Unit>{
    async {
        println("부모 Context / ${Thread.currentThread().name}")
    }

    async(Dispatchers.Default) {
        println("Default / ${Thread.currentThread().name}")
    }

    async(Dispatchers.IO) {
        println("IO / ${Thread.currentThread().name}")
    }

    async(Dispatchers.Unconfined) {
        println("Unconfined / ${Thread.currentThread().name}")
        delay(100L)
        println("Unconfined / ${Thread.currentThread().name}")
    }

    async(newSingleThreadContext("hack")) {
        println("newSingleThreadContext / ${Thread.currentThread().name}")
    }

}