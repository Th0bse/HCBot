package com.th0bse.hcbot.commands

import com.th0bse.hcbot.storage.DatabaseStorageProvider
import dev.kord.core.event.message.MessageCreateEvent

object DumpDB: Command {
    override val name = "dumpDB"
    override val description = "Testing command to look what's in the database"

    override suspend fun execute(event: MessageCreateEvent) {
        DatabaseStorageProvider.getEntityStore().executeInReadonlyTransaction { tnx ->
            tnx.getAll("user").forEach {
                println("${it.getProperty("name")} | ${it.getProperty("tag")} | ${it.getProperty("id")}")
                println("Links:")
                it.getLinks("message").forEach { msg -> println(msg.getBlobString("content"))}
            }
            println("----------------------------------------------")
            tnx.getAll("message").forEach {
                println("${it.getProperty("timestamp")} | ${it.getBlobString("content")} ")
                println("Links:")
                println(it.getLink("author")?.getProperty("name"))
            }
        }
    }
}