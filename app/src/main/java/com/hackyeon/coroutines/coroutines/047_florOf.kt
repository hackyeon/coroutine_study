package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf

fun main() = runBlocking<Unit> {
    flowOf(1, 2, 3, 4, 5).collect {
        println(it)
    }
}