package com.mau89.coroutinepazzlers.utils

import kotlin.time.Duration

val Duration.passed: Duration
    get() = now() - this