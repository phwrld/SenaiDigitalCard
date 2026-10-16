package com.pdrinyo.thesenaidigitalcard.feature.login.presentation

sealed interface LoginEvent {
    data class OnUsuarioChange(val value: String) : LoginEvent
    data class OnSenhaChange(val value: String) : LoginEvent
    data object OnEntrarClick : LoginEvent
    data object OnNavegacaoRealizada : LoginEvent // Nome corrigido (CamelCase)
}