package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno
import kotlinx.serialization.Serializable

/**
 * Mesmo contrato de GET /unidades-curriculares usado no projeto do professor.
 */
@Serializable
data class UnidadeCurricularResponseDto(
    val id: String,
    val nome: String,
    val professor: String,
    val nota1: Double,
    val nota2: Double,
    val media: Double,
    val faltas: Int
) {
    fun toDomain() = UnidadeCurricularAluno(
        id = id,
        materia = nome,
        nota = nota1,
        media = media,
        faltas = faltas
    )
}
