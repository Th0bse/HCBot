package com.th0bse.hcbot.commands

import com.th0bse.hcbot.HCBot
import dev.kord.core.event.message.MessageCreateEvent

class CommandHandler {
    private val registry: CommandRegistry = CommandRegistry()
    val prefix: String = "!"

    init {
        registry.registerCommand(Ping)
    }

    /**
     * Parse a message to a command.
     *
     * @param event The MessageCreateEvent which contains the message to be parsed
     * @return The Command object for the command or null if the message wasn't a command
     * @throws RuntimeException if the message was a command, but the command wasn't found
     */
    fun parse(event: MessageCreateEvent): Command? {
        var messageString = event.message.content
        if (event.message.author!!.isBot) return null // we don't want to parse messages from bots
        if (messageString.startsWith(prefix, true)) { // treat the message as a command, if it starts with the right prefix
            messageString = messageString.removePrefix(prefix)
            val messageSplit = messageString.split(" ")
            val commandString = messageSplit[0]
            var command: Command? = null
            try {
                command = registry.getCommand(commandString)
            } catch (e: RuntimeException) {
                HCBot.logger.writeLogMessage(message = e.message!!)
            }
            return command
        }
        return null
    }

}