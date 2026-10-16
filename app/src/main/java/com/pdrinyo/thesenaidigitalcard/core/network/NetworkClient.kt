package com.pdrinyo.thesenaidigitalcard.core.network

import com.pdrinyo.thesenaidigitalcard.core.auth.SessionTokenStore
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/** Cliente unico: login publico e recursos protegidos com o mesmo token de sessao. */
class NetworkClient(
    private val baseUrl: String,
    private val sessionTokenStore: SessionTokenStore
) {
    private val json = Json { ignoreUnknownKeys = true }
    // Sem logging BODY: nao registrar senha ou Authorization no Logcat.
    private val publicClient by lazy { OkHttpClient.Builder().build() }
    private val authenticatedClient by lazy {
        OkHttpClient.Builder().addInterceptor(AuthInterceptor(sessionTokenStore)).build()
    }
    private fun retrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType())).build()

    fun <T : Any> createPublic(serviceClass: Class<T>): T =
        retrofit(publicClient).create(serviceClass)

    fun <T : Any> createAuthenticated(serviceClass: Class<T>): T =
        retrofit(authenticatedClient).create(serviceClass)
}
