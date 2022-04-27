package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceNumbers(start: Int) = produce<Int> {
    var x = start
    while (true) {
        send(x++)
    }
}

fun CoroutineScope.produceStringNumbers(numbers: ReceiveChannel<Int>, prime: Int): ReceiveChannel<Int> = produce {
    for(i in numbers) {
//        if(i % 2 != 0) send("${i}!")
        if(i % prime != 0) send(i)
    }
}

fun main() = runBlocking<Unit> {
    var numbers = produceNumbers(2) // number: 채널, 리시브 채널, receive 메서드 send 메서드를 호출할수 없다// 채널: 리시브 + 샌드
//    val stringNumbers = produceStringNumbers(numbers)
//    repeat(10) {
//        println(stringNumbers.receive())
//    }


    repeat(10) {
        val prime = numbers.receive()
        println(prime)
        numbers = produceStringNumbers(numbers, prime)
    }
    println("완료")
    coroutineContext.cancelChildren()
}