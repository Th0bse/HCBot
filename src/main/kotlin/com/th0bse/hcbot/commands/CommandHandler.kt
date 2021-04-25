package com.th0bse.hcbot.commands

import com.th0bse.hcbot.HCBot
import com.th0bse.hcbot.logging.LogLevel
import dev.kord.core.event.message.MessageCreateEvent
import org.reflections.Reflections

class CommandHandler {
    val prefix: String = "!"

    init {
        val reflections = Reflections("com.th0bse.hcbot.commands")
        val commands = reflections.getSubTypesOf(Command::class.java)
        for (c in commands) {
            HCBot.logger.writeLogMessage(LogLevel.DEBUG, "Registering Command ${c.name}")
            val objectInstance = c.kotlin.objectInstance
            objectInstance?.registerCommand(objectInstance)
        }
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
                command = CommandRegistry.getCommand(commandString)
            } catch (e: RuntimeException) {
                HCBot.logger.writeLogMessage(message = e.message!!)
            }
            return command
        }
        return null
    }

}