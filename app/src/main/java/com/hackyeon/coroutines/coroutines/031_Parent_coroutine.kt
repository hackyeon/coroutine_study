package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val job = launch { // 부모
            launch(Job()) { // 자식1
                println("launch1: ${Thread.currentThread().name}")
                delay(5000L)
            }

            launch { // 자식2
                println("launch1: ${Thread.currentThread().name}")
                delay(10L)
            }
        }
        job.join()
    }
    println("time: $time")

}