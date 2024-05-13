package com.mau89.coroutinepazzlers.race

import com.mau89.coroutinepazzlers.utils.models.Counter
import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import com.mau89.coroutinepazzlers.utils.threadsScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlin.random.Random.Default.nextLong
import kotlin.time.Duration.Companion.seconds

fun main() {
    runBlocking {
        val time = now()
        val jobs = mutableListOf<Job>()
        val customDispatcher = 2.threadsScheduler

        repeat(1_000) {
            synchronized(this) {
                jobs += launch(customDispatcher) {
                    repeat(1_000) {
                        increment()
                    }
                }
            }
        }

        withTimeout(10.seconds) {
            joinAll(*jobs.toTypedArray())
        }

        print("Final count: ${counter.pretty} in ${time.passed}")
    }
}

private val counter = Counter()

private suspend fun increment() {
    counter.count++
}