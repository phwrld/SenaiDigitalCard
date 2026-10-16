package com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.model

/** Mesmo formato de UnidadeCurricular da API de referencia do Rafael. */
data class UnidadeCurricular(
    val id: String,
    val nome: String,
    val professor: String,
    val nota1: Double,
    val nota2: Double,
    val media: Double,
    val faltas: Int
)
