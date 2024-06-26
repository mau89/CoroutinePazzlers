package com.mau89.coroutinepazzlers.flow

import com.mau89.coroutinepazzlers.utils.now
import com.mau89.coroutinepazzlers.utils.passed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val time = now()
        var result = ""

        stringFlow().flowOn(Dispatchers.IO).map { item ->
            delay(500)
            item
        }.flowOn(Dispatchers.Default).collect { item ->
            result += item
        }
        println("Result: $result ${time.passed}")
    }
}

private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(1000)
    }
}