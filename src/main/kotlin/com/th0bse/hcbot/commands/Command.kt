package com.th0bse.hcbot.commands

import dev.kord.core.event.message.MessageCreateEvent

/**
 * Defines the basic structure of a command.
 *
 * A Command is the object representation of a command that a user issues to the bot.
 */
interface Command {
    val name: String
    val description: String

    /**
     * Defines the behavior of the command.
     *
     * @param event The MessageCreateEvent containing the message in which the user issued the command
     */
    suspend fun execute(event: MessageCreateEvent)

    /**
     * Prints the description or help message for the command.
     *
     * @param event The MessageCreateEvent containing the message in which the user issued the command
     */
    suspend fun printDescription(event: MessageCreateEvent)

}