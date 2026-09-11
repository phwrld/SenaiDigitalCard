package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation

import android.os.Message
import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado

data class  LoginUIState (
    val usuario: String = "",
    val senha: String  = "",
    val erroMensage: String? = null,
    val isLoading: Boolean = false,
    val usuarioLogado: UsuarioLogado? = null
) {
    val loginRealizado: Boolean
        get() = usuarioLogado !=null
}