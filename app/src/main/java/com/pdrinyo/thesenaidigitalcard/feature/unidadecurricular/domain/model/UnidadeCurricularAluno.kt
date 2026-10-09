package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model

/** Mesmo formato de UnidadeCurricular da API de referencia do Rafael. */
data class UnidadeCurricularAluno(
    val id: String,
    val nome: String,
    val professor: String,
    val nota1: Double,
    val nota2: Double,
    val media: Double,
    val faltas: Int
)
