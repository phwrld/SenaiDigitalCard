package com.pdrinyo.thesenaidigitalcard.core.network

import com.pdrinyo.thesenaidigitalcard.core.auth.SessionTokenStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStore: SessionTokenStore
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenStore.obter()
        val original = chain.request()
        val request = if (token.isNullOrBlank()) {
            original
        } else {
            original.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }
        return chain.proceed(request)
    }
}
