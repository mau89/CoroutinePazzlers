package com.mau89.coroutinepazzlers

import kotlin.time.Duration

val Duration.passed: Duration
    get() = now() - this