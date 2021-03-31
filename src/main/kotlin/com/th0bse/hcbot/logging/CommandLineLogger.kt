package com.th0bse.hcbot.logging

import java.time.LocalDateTime

class CommandLineLogger : Logger {
    override val defaultLogLevel: String = "INFO"

    override fun writeLogMessage(logLevel: String, message: String) {
        println(buildLogMessage(logLevel, message))
    }

    override fun setDefaultLogLevel(logLevel: String) {
        TODO("Not yet implemented")
    }

    private fun buildLogMessage(logLevel: String, message: String): String {
        val currentTime = LocalDateTime.now()
        return "$currentTime | $logLevel | $message"
    }
}