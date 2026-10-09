package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.repository

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno

/** Contrato do dominio, separado da API Retrofit. */
interface UnidadeCurricularRepository {
    suspend fun listar(): Result<List<UnidadeCurricularAluno>>
}
