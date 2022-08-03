package se.yobriefca

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import se.yobriefca.plugins.configureRouting
import se.yobriefca.plugins.configureWebjars

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureWebjars()
    }.start(wait = true)
}
