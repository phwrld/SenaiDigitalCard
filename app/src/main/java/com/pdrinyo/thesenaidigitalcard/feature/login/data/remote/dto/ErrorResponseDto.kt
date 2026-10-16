package com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponseDto(val message: String? = null)
