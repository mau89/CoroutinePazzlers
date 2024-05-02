package com.mau89.coroutinepazzlers.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val exceptionHandler = CoroutineExceptionHandler { _, _ -> println("Error") }

        val job = GlobalScope.launch(exceptionHandler) {
            val child1 = launch {
                println("A")
                delay(500)
                println("B")
                throw RuntimeException()
            }

            val child2 = launch {
                println("C")
                delay(1000)
                println("child2")
            }
            joinAll(child1, child2)
        }
        job.join()
    }
}