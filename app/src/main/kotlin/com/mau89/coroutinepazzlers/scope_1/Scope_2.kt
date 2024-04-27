package com.mau89.coroutinepazzlers.scope_1

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val JobA = launch {
            delay(500)
            println("A")
        }

        coroutineScope {
            val LobB = launch {
                delay(1000)
                println("B")
            }
        }

        println("C")
    }
}