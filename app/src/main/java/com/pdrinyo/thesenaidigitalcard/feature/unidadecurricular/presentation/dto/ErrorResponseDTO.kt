package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(
    val message: String? = null
)