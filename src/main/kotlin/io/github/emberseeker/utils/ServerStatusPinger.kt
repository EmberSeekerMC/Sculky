package io.github.emberseeker.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

object ServerStatusPinger {

    private val client: HttpClient = HttpClient.newHttpClient()

    fun ping(ip: String): Pair<String, ServerStatus?> {
        val request = HttpRequest.newBuilder()
            .GET()
            .uri(URI("https://api.minetools.eu/ping/$ip"))
            .build()
        val response = client.send(request, BodyHandlers.ofString())
        val body = response.body()
        if(body.contains("error")) {
            return ip to null
        }
        return ip to Json.decodeFromString<ServerStatus>(body)
    }
}

@Serializable
data class ServerStatus(
    var version: Version,
    var players: Players,
    var description: String,
    var enforceSecureChat: Boolean,
    var previewsChat: Boolean,
    var favicon: String,
    val latency: Float
)

@Serializable
data class Players(
    var max: Int,
    var online: Int,
    var sample: MutableList<ServerListPlayer>
)

@Serializable
data class Version(
    var name: String,
    var protocol: Int
)

@Serializable
data class ServerListPlayer(
    var name: String,
    var uuid: String
)