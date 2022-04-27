package com.hackyeon.coroutines.coroutines

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

// flow 는 현재 coroutine context에서 호출된다
fun simple(): Flow<Int> = flow{ // flow 내에서는 context를 바꿀수 없다
    log("flow를 시작합니다.")
    for(i in 1..10) {
        delay(100L)
        log("값 $i 를 emit한다")
        emit(i)
    } // 업 스트림
}.flowOn(Dispatchers.IO) // 위치 // 업 스트림이 사용할 쓰레드
.map { // 다운 스트림
    it * 2
}.flowOn(Dispatchers.Default) // 여기선 맵의 쓰레드 // 위에서부터 내려오면서 flowOn을 만나면 그 flowOn의 쓰레드를 사용한다
                            // rx는 쓰레드를 먼저 적지만 flow는 사용쓰레드를 마지막에 적을수 있다 적지않을경우 coroutine의 context를 사용한다

fun main() = runBlocking<Unit> {
//    launch(Dispatchers.IO) {
        simple()
            .collect { // 다운 스트림
                log("${it}를 받음")
            }
//    }
}