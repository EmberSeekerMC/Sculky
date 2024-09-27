package io.github.dockyard.io.github.emberseeker

import io.github.cdimascio.dotenv.Dotenv
import java.lang.IllegalArgumentException

val dotenv: Dotenv = Dotenv.load()

inline fun <reified T>getEnv(key: String): T {
    val value = dotenv.get(key) ?: throw IllegalArgumentException("No environment variable with key $key not found")
    return when(T::class) {
        Boolean::class -> (value == "true")     as T
        String::class -> value as T
        Int::class -> value.toInt() as T
        Float::class -> value.toFloat() as T
        Double::class -> value.toDouble() as T
        Long::class -> value.toLong() as T

        else -> throw IllegalArgumentException("Generic is not primitive type")
    }
}