package com.blankthings.baseapp.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class AuthManagerImpl: AuthManager {

    val baseUrl: String = "https://dummyjson.com/test"
    val client = HttpClient()

    override suspend fun loginWithEmailAndPassword(email: String, password: String): LoginResult {
        val text = client.get(baseUrl).bodyAsText()
        client.close()
        println("------->" + text)

        return if (email.contains("admin")) {
            LoginResult.Success("accessToken", "uuid", true)
        } else {
            LoginResult.Failed("", "Invalid credentials")
        }
    }
}