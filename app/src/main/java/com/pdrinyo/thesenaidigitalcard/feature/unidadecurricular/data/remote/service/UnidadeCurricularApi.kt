package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.data.remote.service

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.data.remote.dto.UnidadeCurricularDto
import retrofit2.http.GET

interface UnidadeCurricularApi {
    @GET("unidades-curriculares")
    suspend fun listar(): List<UnidadeCurricularDto>
}
