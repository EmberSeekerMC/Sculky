package io.github.emberseeker.commands

import io.github.emberseeker.Environment
import io.github.emberseeker.Sculky

abstract class StaffServerCommand: Command {

    suspend fun register() {
        Sculky.kord.createGuildChatInputCommand(Environment.STAFF_GUILD_ID, name, description)
        CommandManager.map[name] = this
    }
}