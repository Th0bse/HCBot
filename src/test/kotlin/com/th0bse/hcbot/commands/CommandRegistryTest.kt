package com.th0bse.hcbot.commands

import dev.kord.core.event.message.MessageCreateEvent
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll

internal class CommandRegistryTest {

    @Test
    fun registerCommand() {
        CommandRegistry.registerCommand(TestCommand)
        assertEquals(CommandRegistry.getCommand("testCommand"), TestCommand)
    }

    // Command object used for testing. We create this here and don't use an existing command like Ping, to have full
    // control over its behavior.
    object TestCommand: Command {
        override val name: String = "testCommand"
        override val description: String = "This is a simple command created for testing purposes."

        override suspend fun execute(event: MessageCreateEvent) {
            event.message.getChannel().createMessage("Success")
        }

        override suspend fun printDescription(event: MessageCreateEvent) {
            event.message.getChannel().createMessage(description)
        }

    }
}