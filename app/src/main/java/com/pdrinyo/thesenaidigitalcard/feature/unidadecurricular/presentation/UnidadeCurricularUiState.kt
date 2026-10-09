package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno

data class UnidadeCurricularUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricularAluno> = emptyList(),
    val errorMessage: String? = null
)
