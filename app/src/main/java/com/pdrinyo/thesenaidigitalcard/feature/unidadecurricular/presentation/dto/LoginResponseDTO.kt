package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto

import kotlinx.serialization.Serializable

/**
 * Campos da API de Rafael: id, nome, matricula, curso, turma e token.
 * tipo e opcional: a API de referencia nao o devolve.
 */
@Serializable
data class LoginResponseDto(
    val id: String? = null,
    val nome: String = "",
    val matricula: String = "",
    val curso: String = "",
    val turma: String = "",
    val token: String? = null,
    val tipo: String? = null
)
