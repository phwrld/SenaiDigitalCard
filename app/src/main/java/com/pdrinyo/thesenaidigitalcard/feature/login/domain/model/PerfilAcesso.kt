package com.pdrinyo.thesenaidigitalcard.feature.login.domain.model

enum class PerfilAcesso {
    ALUNO, PROFESSOR;
    companion object {
        fun fromApi(value: String): PerfilAcesso = when(value.trim().uppercase()) {
            "ALUNO" -> ALUNO
            "PROFESSOR" -> PROFESSOR
            else -> throw IllegalArgumentException("Perfil de acesso inválido: $value")
        }
    }
}
