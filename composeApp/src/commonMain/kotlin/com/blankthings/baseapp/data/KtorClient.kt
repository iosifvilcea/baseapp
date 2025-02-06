package com.blankthings.baseapp.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.*

object KtorClient {

    private const val BASE_URL: String = "https://10.0.2.2:8443"

    fun create(): HttpClient {
        return HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            defaultRequest {
                url(BASE_URL)
            }
        }
    }
}