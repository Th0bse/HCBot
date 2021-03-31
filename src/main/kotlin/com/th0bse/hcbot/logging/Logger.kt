package com.th0bse.hcbot.logging

/**
 * Basic structure for a self-built logger class used to log internal bot stuff, both for debugging and for error logs.
 */
interface Logger {
    val defaultLogLevel: String

    /**
     * Write a log message with the specified log level.
     *
     * @param loglevel The log level to be used
     * @param message The message to be written to the log
     */
    fun writeLogMessage(logLevel: String = defaultLogLevel, message: String)

    /**
     * Set the default log level.
     *
     * @param loglevel The log level that should be set as the default
     */
    fun setDefaultLogLevel(logLevel: String)
}