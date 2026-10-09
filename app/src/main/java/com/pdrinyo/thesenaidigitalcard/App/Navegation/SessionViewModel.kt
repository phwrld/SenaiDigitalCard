package com.pdrinyo.thesenaidigitalcard.App.Navegation

import androidx.lifecycle.ViewModel
import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.network.NetworkFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionViewModel : ViewModel() {
    private val _usuarioLogado = MutableStateFlow<UsuarioLogado?>(null)
    val usuarioLogado: StateFlow<UsuarioLogado?> = _usuarioLogado.asStateFlow()

    fun setUsuarioLogado(usuario: UsuarioLogado) {
        NetworkFactory.sessionTokenStore.salvar(usuario.token)
        _usuarioLogado.value = usuario
    }

    fun limparSession() {
        NetworkFactory.sessionTokenStore.limpar()
        _usuarioLogado.value = null
    }
}
