package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: String? = null,
    val nome: String = "",
    val curso: String = "",
    val turma: String = "",
    val token: String? = null,
    val tipo: String? = null // Adicionado valor padrão 'null' para não quebrar a deserialização
)