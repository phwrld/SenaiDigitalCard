package com.pdrinyo.thesenaidigitalcard.feature.home.domain

data class UsuarioLogado(
    val id: String? = null,
    val nome: String = "",
    val curso: String = "",
    val turma: String = "",
    val token: String? = null,
    val tipo: String? = "ALUNO"
)