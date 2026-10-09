package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.network

import com.pdrinyo.thesenaidigitalcard.core.auth.SessionTokenStore
import com.pdrinyo.thesenaidigitalcard.core.network.AuthInterceptor
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.service.AuthApi
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.data.remote.service.UnidadeCurricularApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/**
 * A mesma base e o mesmo token sao usados pela tela de login e pelas UCs.
 * 10.0.2.2 funciona no emulador Android quando a API roda no computador.
 */
object NetworkFactory {
    private const val BASE_URL = "http://10.0.2.2:8080/"
    val sessionTokenStore = SessionTokenStore()
    private val json = Json { ignoreUnknownKeys = true }

    // Nao registramos corpos de requisicoes para evitar expor senhas e tokens.
    private val publicClient = OkHttpClient.Builder().build()
    private val authenticatedClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(sessionTokenStore))
        .build()

    private fun createRetrofit(baseUrl: String, authenticated: Boolean): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(if (authenticated) authenticatedClient else publicClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    fun createAuthApi(baseUrl: String = BASE_URL): AuthApi =
        createRetrofit(baseUrl, false).create(AuthApi::class.java)

    fun createUnidadeCurricularApi(baseUrl: String = BASE_URL): UnidadeCurricularApi =
        createRetrofit(baseUrl, true).create(UnidadeCurricularApi::class.java)
}
