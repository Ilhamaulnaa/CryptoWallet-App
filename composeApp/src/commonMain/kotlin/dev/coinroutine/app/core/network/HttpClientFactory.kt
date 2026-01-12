package dev.coinroutine.app.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = 20_000L
                requestTimeoutMillis = 20_000L
            }
            install(HttpCache)
            defaultRequest {
                headers {
                    append(
                        "x-access-token",
                        "coinranking6dae880a1f3338e499237c3dd0bdf1a69fd5d0bd91b5546c"
                    )
                }
                contentType(ContentType.Application.Json)
            }
        }
    }
}

//object HttpClientFactory {
//
//    fun create(engine: HttpClientEngine): HttpClient {
//
//        return HttpClient(engine) {
//            //berfungsi untuk mengubah (deserialisasi) respon JSON dari API menjadi objek Kotlin secara otomatis.
//            install(ContentNegotiation) {
//                json(
//                    json = Json {
//                        ignoreUnknownKeys = true
//                    }
//                )
//            }
//            install(HttpTimeout) {
//                socketTimeoutMillis = 20_000L
//                requestTimeoutMillis = 20_000L
//            }
//            install(HttpCache)
//            //mendefinisikan apa yang akan selalu dikirim pada setiap request yang dibuat oleh client
//            defaultRequest {
//                headers { append("x-access-token", "coinranking9ce5a01a8185ad079a954b40e5bae7f3c81238639c547926") }
//                contentType(ContentType.Application.Json)
//            }
//        }
//
//    }
//
//}