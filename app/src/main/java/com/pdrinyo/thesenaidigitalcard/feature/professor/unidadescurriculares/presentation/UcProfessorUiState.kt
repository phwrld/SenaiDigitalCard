package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation

import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.dto.UcProfessorDto

data class UcProfessorUiState(
    val loading: Boolean = true,
    val itens: List<UcProfessorDto> = emptyList(),
    val erro: String? = null
)
