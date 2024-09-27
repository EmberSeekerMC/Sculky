package io.github.emberseeker

import dev.kord.common.entity.Snowflake
import io.github.dockyard.io.github.emberseeker.getEnv

object Environment {
    val STAFF_GUILD_ID = Snowflake(1288953850119585873)
    val DISCORD_TOKEN = getEnv<String>("DISCORD_TOKEN")
}


