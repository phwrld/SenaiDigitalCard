package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.service

import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.dto.UcProfessorDto
import retrofit2.http.GET

interface UcProfessorApi {
    @GET("professores/me/unidades-curriculares")
    suspend fun listar(): List<UcProfessorDto>
}
