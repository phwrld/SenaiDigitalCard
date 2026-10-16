package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.model

data class Turma(
    val id: String,
    val nome: String,
    val curso: String,
    val quantidadeAlunos: Int,
    val horario: String,
    val semestre: String
)