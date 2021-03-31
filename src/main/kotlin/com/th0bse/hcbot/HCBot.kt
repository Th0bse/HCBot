package com.th0bse.hcbot

import com.th0bse.hcbot.logging.CommandLineLogger
import com.th0bse.hcbot.logging.Logger
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.runBlocking
import java.io.File


class HCBot {

    private var kord: Kord
    private val logger: Logger

    init {
        runBlocking {
            kord = Kord(getApiToken())
        }

        logger = CommandLineLogger()
    }

    /**
     * Main function of the bot.
     *
     * All initialization happens in here, and this function (which gets called in the "actual" main function with
     * runBlocking{ }, will keep running to keep the bot alive, blocking the program from just running once and then
     * exiting.
     */
    suspend fun main() {
        registerListeners()
        // All listeners need to be registered above this line, since login() blocks the main coroutine, which means
        // that nothing after this line gets executed.
        kord.login()
    }

    /**
     * Registers all relevant listener functions.
     */
    private fun registerListeners() {
        kord.on<MessageCreateEvent> {logger.writeLogMessage(message = message.content)}
    }

    /**
     * Gets the Discord API Token from resources.
     *
     * The token is stored in a *.properties file called bot.properties, which is stored in the resources directory of
     * the bot, src/main/resources/.
     *
     * @return The API Token as String
     */
    private fun getApiToken(): String {
        val properties = File("./src/main/resources", "bot.properties").inputStream().use {
            java.util.Properties().apply { load(it) }
        }
        return properties.getValue("DISCORD_TOKEN") as String
    }
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val hcbot = HCBot()
            runBlocking {
                hcbot.main()
            }
        }
    }
}