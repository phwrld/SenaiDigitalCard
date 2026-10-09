package com.pdrinyo.thesenaidigitalcard.feature.home.domain

data class UsuarioLogado(
    val id: String? = null,
    val nome: String = "",
    val matricula: String = "",
    val curso: String = "",
    val turma: String = "",
    val token: String? = null,
    // Somente uma resposta explicita da API libera as telas do professor.
    val tipo: String? = "ALUNO"
)
