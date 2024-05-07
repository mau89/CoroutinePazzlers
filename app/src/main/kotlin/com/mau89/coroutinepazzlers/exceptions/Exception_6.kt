package com.mau89.coroutinepazzlers.exceptions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() {
    runBlocking {
        val exceptionHandler = CoroutineExceptionHandler { _, _ -> println("Error") }
        val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

        val child1 = scope.launch {
            println("A")
            delay(500)
            println("B")
            throw Exception()
        }

        val child2 = scope.launch {
            println("C")
            delay(1000)
            println("Success")
        }
        joinAll(child1, child2)
    }
}