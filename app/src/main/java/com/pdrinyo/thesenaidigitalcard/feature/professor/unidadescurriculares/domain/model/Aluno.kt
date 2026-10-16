package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.model

data class Aluno(
    val id: String,
    val nome: String,
    val faltas: Int = 0
)