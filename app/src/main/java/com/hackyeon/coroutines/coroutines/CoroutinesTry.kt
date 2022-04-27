package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import java.lang.Exception


suspend fun doTry() = coroutineScope {
    val job1 = launch {
        try {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        } finally {
            println("job1 is finished")
        }
    }

    val job2 = launch {
        try {
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("2!")
        } finally {
            println("job2 is finished")
        }
    }

    val job3 = launch {
        try {
            println("launch3: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }finally {
            println("job3 is finished")
        }
    }

    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()

    println("4!")




}

fun main() = runBlocking {
    doTry()
}