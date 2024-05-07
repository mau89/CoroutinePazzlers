package com.mau89.coroutinepazzlers.flow

import android.view.textclassifier.ConversationActions.Request
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        requestFlow()
            .map { request -> performRequest(request) }
            .catch { e -> emit( e.localizedMessage ) }
            .collect { response -> println(response) }
    }
}

fun requestFlow() = flow {
    for (i in 1..3) {
        emit(i)
    }
}

suspend fun performRequest(request: Int): String {
    delay(100)
    if (request == 2) throw RuntimeException("Error")
    return "$request"
}