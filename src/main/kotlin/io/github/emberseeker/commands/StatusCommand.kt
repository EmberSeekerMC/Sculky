package io.github.emberseeker.commands

import dev.kord.core.behavior.interaction.response.DeferredPublicMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.edit
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.entity.interaction.InteractionCommand
import io.github.emberseeker.utils.ServerStatusPinger

class StatusCommand: StaffServerCommand() {
    override val name: String = "status"
    override val description: String = "gets status of servers"

    override suspend fun handle(command: InteractionCommand, response: DeferredPublicMessageInteractionResponseBehavior) {
        val responseMessage = response.respond { content = "Pinging servers.." }

        val buildServerStatus = ServerStatusPinger.ping("ember-build.lukynka.codes")
        val devServerStatus = ServerStatusPinger.ping("ember-dev.lukynka.codes")
        val servers = listOf(buildServerStatus, devServerStatus)

        val message = buildString {
            servers.forEach { server ->
                val ip = server.first
                val status = server.second
                if(status == null) appendLine(":small_red_triangle_down: `${ip}` - offline/unreachable") else appendLine(":white_check_mark: `${ip}` - **${status.players.online}/${status.players.max}** players online")
            }
        }

        responseMessage.edit { content = message }
    }
}