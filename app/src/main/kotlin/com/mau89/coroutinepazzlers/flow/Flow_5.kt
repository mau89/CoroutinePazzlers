package com.mau89.coroutinepazzlers.flow

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration
import kotlin.time.Duration.Companion.ZERO
import kotlin.time.Duration.Companion.milliseconds

fun main() {
    runBlocking {
        val sharedFlow = stringFlow().shareIn(this, SharingStarted.Lazily, 0)
        launch {
            sharedFlow.collect {
                println(it)
            }
        }
        launch {
            sharedFlow
                .timed(1000.milliseconds)
                .collect {
                    println(it)
                }
        }
    }
}

private fun Flow<String>.timed(delay: Duration): Flow<String> = flow {
    var time: Duration = Duration.ZERO
    buffer(1, BufferOverflow.DROP_OLDEST).collect { item ->
        if (time == ZERO) {
            time = now()
        }
        emit("${time.passed} $item")
        delay(delay)
    }
}

private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(50)
    }
}