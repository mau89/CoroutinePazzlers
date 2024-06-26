package com.mau89.coroutinepazzlers.schedulers

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import com.mau89.coroutinepazzlers.utils.threadsScheduler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

fun main() {
    runBlocking {
        val customDispatcher = 1.threadsScheduler
        val time = now()

        val task1 = async(customDispatcher) {
            heavyComputation(taskId = 1)
        }

        val task2 = async(customDispatcher) {
            heavyComputation(taskId = 2)
        }

        val task3 = async(customDispatcher) {
            heavyComputation(taskId = 3)
        }

        println("\nResult: ${task1.await() + task2.await() + task3.await()}")

        println("Total time: ${time.passed}")
    }
}

private suspend fun heavyComputation(taskId: Int): Int {
    print("Task $taskId started")
    delay(1.seconds)
    print("Task $taskId completed")
    return taskId
}