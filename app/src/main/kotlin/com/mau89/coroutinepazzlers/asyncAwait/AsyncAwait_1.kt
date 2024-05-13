package com.mau89.coroutinepazzlers.asyncAwait

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        val time = now()

        val deferred1 = async { performTask(1) }
        val deferred2 = async { performTask(2) }

        println("A ")
        val result1 = deferred1.await()
        val result2 = deferred2.await()

        println("$result1 $result2 ${(time.passed)}")
    }
}

suspend fun performTask(id: Int): Int {
    delay(1000)
    return id * 2
}
