package io.github.emberseeker.commands

import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.entity.interaction.InteractionCommand

class GuyCommand: StaffServerCommand() {
    override val name: String = "guy"
    override val description: String = "a guy"

    override suspend fun handle(command: InteractionCommand, response: DeferredPublicMessageInteractionResponseBehavior) {
        response.respond { content = "just a little guy" }
    }
}