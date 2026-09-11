package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.service


import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.LoginRequestDTO
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequestDTO): LoginResponseDto
}