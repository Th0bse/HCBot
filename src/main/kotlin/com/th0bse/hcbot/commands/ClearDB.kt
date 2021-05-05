package com.th0bse.hcbot.commands

import com.th0bse.hcbot.storage.DatabaseStorageProvider
import dev.kord.core.event.message.MessageCreateEvent

object ClearDB: Command {
    override val name = "clearDB"
    override val description = "Debug command to clear the database"

    override suspend fun execute(event: MessageCreateEvent) {
        DatabaseStorageProvider.clearDatabase()
    }
}