package com.mau89.coroutinepazzlers.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

fun main() {
    runBlocking {
        withTimeoutOrNull(250) {
            numberFlow().collect {
                delay(50)
                println("$it")
            }
        }
    }
}

fun numberFlow(): Flow<Int> = flow {
    repeat(3) {
        delay(100)
        emit(Random.nextInt(10))
    }
}