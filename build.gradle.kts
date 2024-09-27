plugins {
    application
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.22"
    id("io.ktor.plugin") version "2.2.3"

}

group = "io.github.emberseeker"
version = "0.1"

repositories {
    mavenCentral()
    maven("https://mvn.devos.one/releases")
}

dependencies {
    implementation("dev.kord:kord-core:0.14.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("cz.lukynka:pretty-log:1.4")
    implementation("org.slf4j:slf4j-simple:2.0.16")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}