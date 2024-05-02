package com.mau89.coroutinepazzlers.cancellation

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            try {
                repeat(5) { i ->
                    println("A$i")
                    delay(100)
                }
            } finally {
                println("B")
            }
        }

        delay(250)
        println("C")
        job.cancelAndJoin()
        println("D")
    }
}