package com.mau89.coroutinepazzlers.flow

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(FlowPreview::class)
fun main() {
    runBlocking {
        val time = now()
        var result = ""

        stringFlow().flatMapMerge { value ->
            flow {
                withContext(Dispatchers.IO) {
                    delay(100)
                    emit(value)
                }
            }
        }.collect { item ->
            result += item
        }
        println("Result: $result ${time.passed}")
    }
}

private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(50)
    }
}