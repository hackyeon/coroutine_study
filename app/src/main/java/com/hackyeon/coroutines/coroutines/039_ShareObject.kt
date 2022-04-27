package com.hackyeon.coroutines.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis



suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100 // 시작할 코루틴 갯수
    val k = 1000 // 코루틴 내에서 반복할 횟수
    val elapsed = measureTimeMillis {
        coroutineScope {    // scope for coroutine
            repeat(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
        }
    }
    println("$elapsed ms동안 ${n * k}개의 액션을 수행했습니다.")
}

var counter = 0
// 공유객체를 사용해야할경우 coroutineContext를 만들어서 하거나 mutex를 만든다
val counterContext = newSingleThreadContext("counter")
val mutex = Mutex()

// 최신기술 액터로 액터가 데이터를 관리해서 코루틴이 데이터에 접근할경우 액터를 통해서만 접근할수있다
sealed class CounterMsg{
    object IncCounter: CounterMsg()
    class GetCounter(val response: CompletableDeferred<Int>): CounterMsg()
}
fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0 // 액터 안에 상태를 캡술화해두고 다른 코루틴이 접근하지 못하게 한다

    for(msg in channel){ // 외부에서 보내는 것은 채널을 통해서만 받을수 있다 (receive)
        when(msg) {
            is CounterMsg.IncCounter -> counter++ // 증가시키는 신호
            is CounterMsg.GetCounter -> msg.response.complete(counter) // 현재상태를 반환한다
        }
    }
}

fun main() = runBlocking<Unit> {
//    withContext(counterContext) {  // 전체코드를 하나의 스레드에서
    val myCounterActor = counterActor()
    withContext(Dispatchers.Default) {
        massiveRun {
//            withContext(counterContext){ // 하나의코드를 하나의 스레드에서
//                counter++
//            }
//            mutex.withLock { // 쓰레드가 하나씩만 진입할수 있다
//                counter++
//            }
            myCounterActor.send(CounterMsg.IncCounter) // actor를 이용한 방식 아직 많이 쓰이진않지만 알고는 있어야할거같다
        }
    }
//    println("counter: $counter")


    val response = CompletableDeferred<Int>()
    myCounterActor.send(CounterMsg.GetCounter(response))
    println("counter: ${response.await()}")
    myCounterActor.close()

}