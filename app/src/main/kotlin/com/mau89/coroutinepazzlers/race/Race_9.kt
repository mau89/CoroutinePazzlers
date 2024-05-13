package com.mau89.coroutinepazzlers.race

import com.mau89.coroutinepazzlers.utils.models.Counter
import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import com.mau89.coroutinepazzlers.utils.threadsScheduler
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import kotlin.time.Duration.Companion.seconds

fun main() {
    runBlocking {
        val time = now()
        val jobs = mutableListOf<Job>()
        val customDispatcher = 100.threadsScheduler

        repeat(1_000) {
            mutex.lock()
            jobs += launch(customDispatcher) {
                repeat(1_000) {
                    increment()
                }
            }
            mutex.unlock()
        }

        withTimeout(10.seconds) {
            joinAll(*jobs.toTypedArray())
        }

        print("Final count: ${counter.pretty} in ${time.passed}")
    }
}

private val counter = Counter()
private val mutex = Mutex()

private fun increment() {
    counter.count++
}