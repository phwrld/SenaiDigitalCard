package com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(val login: String, val senha: String)
