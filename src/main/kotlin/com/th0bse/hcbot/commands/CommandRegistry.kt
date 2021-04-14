package com.th0bse.hcbot.commands

/**
 * The CommandRegistry holds references to all Command objects, and gives other classes access to those commands.
 */
class CommandRegistry {
    val commands: HashMap<String, Command> = HashMap()

    /**
     * Register a new command.
     *
     * @param command The command to be registered
     */
    fun registerCommand(command: Command) {
        if (commands.containsKey(command.name)) {
            throw RuntimeException("Cannot register command ${command.name}, since" +
                    "its already registered, or a command with an identical name already exists.")
        }
        commands.put(command.name, command)
    }

    /**
     * Get a specific command.
     *
     * @param name The name of the command
     */
    fun getCommand(name: String): Command {
        return if (commands.containsKey(name)) commands[name]!! else
            throw RuntimeException("The command could not be found.")
    }
}