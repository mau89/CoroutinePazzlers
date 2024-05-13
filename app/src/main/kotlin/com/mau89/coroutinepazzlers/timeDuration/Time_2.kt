package com.mau89.coroutinepazzlers.timeDuration

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main() {
    val time = now()

    runBlocking {
        repeat(10) {
            launch {
                sleep(100)
                println("$it: ${time.passed}, ")
            }
        }
    }

    println("\nTotal: ${time.passed}")
}
