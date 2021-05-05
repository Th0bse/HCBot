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
     * Registers the command with the CommandRegistry.
     *
     * This needs to be called for every Command that gets created. Since I don't seem to find a way to force any class
     * that is implementing this interface to do certain stuff in it's init {} block, this and the convention that this
     * function has to be called first thing is the closest I can get.
     */
    fun registerCommand(command: Command) {
        CommandRegistry.registerCommand(command)
    }

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
    suspend fun printDescription(event: MessageCreateEvent) {
        event.message.getChannel().createMessage(description)
    }

}