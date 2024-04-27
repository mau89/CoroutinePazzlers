package com.mau89.coroutinepazzlers.scope_1

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(): Unit {
    runBlocking {
        val JobA = launch {
            delay(500)
            println("A")
        }

        val JobB = launch {
            delay(1000)
            println("B")
        }

        println("C")
    }
}

