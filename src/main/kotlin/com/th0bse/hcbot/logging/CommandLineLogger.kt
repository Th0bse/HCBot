package com.th0bse.hcbot.logging

import java.time.LocalDateTime

class CommandLineLogger : Logger {
    override var defaultLogLevel: LogLevel = LogLevel.INFO

    override fun writeLogMessage(logLevel: LogLevel, message: String) {
        println(buildLogMessage(logLevel, message))
    }

    private fun buildLogMessage(logLevel: LogLevel, message: String): String {
        val currentTime = LocalDateTime.now()
        return "$currentTime | ${logLevel.displayName} | $message"
    }
}