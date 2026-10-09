package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.service

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.UnidadeCurricularResponseDto
import retrofit2.http.GET

interface UnidadeCurricularApi {
    @GET("unidades-curriculares")
    suspend fun listar(): List<UnidadeCurricularResponseDto>
}
