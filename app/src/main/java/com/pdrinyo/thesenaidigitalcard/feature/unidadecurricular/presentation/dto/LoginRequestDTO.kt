package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    @SerialName("login")
    val usuario: String,
    val senha: String
)