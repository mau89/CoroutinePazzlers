package com.mau89.coroutinepazzlers.timeDuration

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() {
    runBlocking {
        val time = now()

        val job1 = launch {
            println("A")
            delay(1000L)
            println("B")
        }

        val job2 = launch {
            println("C")
            sleep(1000L)
            println("D")
        }

        val job3 = launch {
            println("E")
            delay(2000L)
            println("F")
        }

        joinAll(job1, job2, job3)
        println(" ${time.passed}")
    }
}