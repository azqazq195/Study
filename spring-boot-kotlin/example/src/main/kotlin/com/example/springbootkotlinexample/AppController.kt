package com.example.springbootkotlinexample

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
@RequestMapping("/")
class AppController(
    private val environment: Environment
) {
    @GetMapping()
    fun hello(): String {
        return "Hello, world!"
    }

    @GetMapping("health-check")
    fun healthCheck(): String {
        return "OK"
    }

    @GetMapping("index")
    fun index(): IndexResponse {
        return IndexResponse(environment)
    }
}

class IndexResponse(
    val h2Console: String,
    val swagger: String
) {
    constructor(environment: Environment) : this(
        "http://${InetAddress.getLoopbackAddress().hostAddress}:${
        environment.getProperty(
            "server.port",
            Int::class.java,
            8080
        )
        }/h2-console",
        "http://${InetAddress.getLocalHost().hostAddress}:${
        environment.getProperty(
            "server.port",
            Int::class.java,
            8080
        )
        }/swagger-ui/index.html"
    )
}
