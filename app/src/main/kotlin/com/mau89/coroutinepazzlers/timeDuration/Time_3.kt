package com.mau89.coroutinepazzlers.timeDuration

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val time = now()

    runBlocking {
        launch {
            repeat(10) {
                delay(100)
                println("$it: ${time.passed} 👉")
            }
        }
    }

    println("\nTotal: ${time.passed}")
}
