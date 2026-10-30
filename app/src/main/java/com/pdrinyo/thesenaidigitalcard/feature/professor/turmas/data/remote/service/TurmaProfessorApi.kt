package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.service

import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.dto.TurmaProfessorDto
import retrofit2.http.GET

interface TurmaProfessorApi {
    @GET("professores/me/turmas")
    suspend fun listar(): List<TurmaProfessorDto>
}
