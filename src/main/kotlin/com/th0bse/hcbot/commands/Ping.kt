package com.th0bse.hcbot.commands

import dev.kord.core.event.message.MessageCreateEvent

/**
 * A simple ping command, to test if the bot responds to user issued commands.
 */
object Ping: Command {
    override val name: String = "ping"
    override val description: String =
        "If you say ping, I say pong"

    override suspend fun execute(event: MessageCreateEvent) {
        event.message.getChannel().createMessage("Pong")
    }

    // currently never gets called, since the feature to supply arguments when calling a command is not yet implemented
    override suspend fun printDescription(event: MessageCreateEvent) {
        event.message.getChannel().createMessage(description)
    }
}