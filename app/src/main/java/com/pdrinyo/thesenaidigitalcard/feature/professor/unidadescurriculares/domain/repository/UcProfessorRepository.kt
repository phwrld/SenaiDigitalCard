package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.repository

import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.dto.UcProfessorDto

interface UcProfessorRepository {
    suspend fun listar(): Result<List<UcProfessorDto>>
}
