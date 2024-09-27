package io.github.emberseeker.commands

import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.entity.interaction.InteractionCommand

interface Command {
    val name: String
    val description: String
    suspend fun handle(command: InteractionCommand, response: DeferredPublicMessageInteractionResponseBehavior)
}