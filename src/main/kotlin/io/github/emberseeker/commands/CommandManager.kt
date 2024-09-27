package io.github.emberseeker.commands

import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.entity.interaction.InteractionCommand

object CommandManager {
    val map: MutableMap<String, Command> = mutableMapOf()

    suspend fun handle(command: InteractionCommand, response: DeferredPublicMessageInteractionResponseBehavior) {
        val mapped = map[command.rootName] ?: return
        mapped.handle(command, response)
    }
}