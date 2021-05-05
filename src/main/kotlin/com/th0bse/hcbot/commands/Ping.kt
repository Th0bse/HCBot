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
}