package io.github.emberseeker

import cz.lukynka.prettylog.LogType
import cz.lukynka.prettylog.LoggerSettings
import cz.lukynka.prettylog.LoggerStyle
import cz.lukynka.prettylog.log
import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import io.github.emberseeker.Sculky.kord
import io.github.emberseeker.commands.CommandManager
import io.github.emberseeker.commands.GuyCommand
import io.github.emberseeker.commands.StatusCommand

object Sculky {
    lateinit var kord: Kord
}

@OptIn(PrivilegedIntent::class)
suspend fun main() {
    LoggerSettings.loggerStyle = LoggerStyle.BRACKET_PREFIX
    log("Loading Sculky..", LogType.DEBUG)

    log("Authenticating to Discord..", LogType.NETWORK)
    kord = Kord(Environment.DISCORD_TOKEN)

    GuyCommand().register()
    StatusCommand().register()

    kord.on<GuildChatInputCommandInteractionCreateEvent> {
        val response = interaction.deferPublicResponse()
        val command = interaction.command
        log("Received command /${command.rootName}")

        CommandManager.handle(command, response)
    }

    kord.login {
        presence { playing("Ember Seeker") }
        intents += Intent.MessageContent
        log("Sculky running!", LogType.SUCCESS)
    }
}
