package com.th0bse.hcbot.storage

import java.time.Instant

data class Message(
    val author: User,
    val timestamp: Instant,
    val content: String
)
