package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlin.random.Random

// superVisorJob 은 아래방향으로만 cancel을 보낸다
// ex) job2 에서 예외가 발생한경우 job2의 자식에게만 영향을 주게된다

suspend fun superPrintRan1(){
    delay(1000L)
    println(Random.nextInt(0, 50))
}

suspend fun superPrintRan2() {
    delay(50L)
    throw ArithmeticException()
}

val superCeh = CoroutineExceptionHandler { _, exception ->
    println("something happend: $exception")

}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + superCeh)
    val job1 = scope.launch { superPrintRan1() }
    val job2 = scope.launch { superPrintRan2() }
    joinAll(job1, job2)
}