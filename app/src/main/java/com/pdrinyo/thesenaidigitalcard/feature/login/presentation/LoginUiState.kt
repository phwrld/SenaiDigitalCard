package com.pdrinyo.thesenaidigitalcard.feature.login.presentation

import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado

data class  LoginUiState (
    val usuario: String = "",
    val senha: String  = "",
    val erroMensage: String? = null,
    val isLoading: Boolean = false,
    val usuarioLogado: UsuarioLogado? = null
) {
    val loginRealizado: Boolean
        get() = usuarioLogado !=null
}