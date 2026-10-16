package com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

/** 'perfil' e o campo de permissao da API do professor. */
@Serializable
data class LoginResponseDto(
    val id: String = "",
    val nome: String = "",
    val matricula: String = "",
    val perfil: String? = null,
    val curso: String = "",
    val turma: String = "",
    val token: String? = null,
    val tipo: String? = null
)
