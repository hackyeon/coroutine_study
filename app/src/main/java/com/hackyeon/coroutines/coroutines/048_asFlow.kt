package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect

fun main() = runBlocking {
    listOf(1, 2, 3, 4, 5).asFlow().collect { println(it) }
    (6..10).asFlow().collect { println(it) }
}