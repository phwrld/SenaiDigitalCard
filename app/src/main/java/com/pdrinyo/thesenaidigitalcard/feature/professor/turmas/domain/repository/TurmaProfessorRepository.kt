package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.repository

import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.dto.TurmaProfessorDto

interface TurmaProfessorRepository {
    suspend fun listar(): Result<List<TurmaProfessorDto>>
}
