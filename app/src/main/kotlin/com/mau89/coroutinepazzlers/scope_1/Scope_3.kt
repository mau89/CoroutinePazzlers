package com.mau89.coroutinepazzlers.scope_1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import threadName
import threadsScheduler

fun main() {
    runBlocking {
        val context1 = 1.threadsScheduler("1")
        val context2 = 1.threadsScheduler("2")

        launch(context1) {
            println("A $threadName")

            withContext(context2) {
                delay(500)
                println("B $threadName")
            }


            println("C $threadName")
        }
    }
}