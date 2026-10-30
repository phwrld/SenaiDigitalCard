package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation

import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.dto.TurmaProfessorDto

data class TurmaProfessorUiState(
    val loading: Boolean = true,
    val itens: List<TurmaProfessorDto> = emptyList(),
    val erro: String? = null
)
