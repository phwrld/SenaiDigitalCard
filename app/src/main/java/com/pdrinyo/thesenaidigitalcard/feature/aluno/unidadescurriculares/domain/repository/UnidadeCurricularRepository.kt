package com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.repository

import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

/** Contrato do dominio, separado da API Retrofit. */
interface UnidadeCurricularRepository {
    suspend fun listar(): Result<List<UnidadeCurricular>>
}
