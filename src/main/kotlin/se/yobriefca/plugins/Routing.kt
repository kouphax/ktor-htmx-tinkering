package se.yobriefca.plugins

import se.yobriefca.Countries
import com.mitchellbosecke.pebble.loader.ClasspathLoader
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.pebble.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    install(Pebble) {
        loader(ClasspathLoader().apply {
            prefix = "templates"
        })
    }

    routing {
        get("/") {
            call.respond(PebbleContent("index.html", model = mapOf()))
        }

        get("/countries") {
            val query = call.request.queryParameters.get("q") ?: ""
            call.respond(PebbleContent("results.html", mapOf(
                "countries" to Countries.all.filter {
                    it.contains(query, ignoreCase = true)
                }
            )))
        }
    }
}
