package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.data.remote.dto

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno
import kotlinx.serialization.Serializable

@Serializable
data class UnidadeCurricularDto(
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
        nome = nome,
        professor = professor,
        nota1 = nota1,
        nota2 = nota2,
        media = media,
        faltas = faltas
    )
}
