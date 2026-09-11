package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository.LoginRepository
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository.LoginRepositoryProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository = LoginRepositoryProvider.provide()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.OnUsuarioChange -> {
                _uiState.update { state ->
                    state.copy(
                        usuario = event.value,
                        erroMensage = null
                    )
                }
            }

            is LoginUIEvent.OnSenhaChange -> {
                _uiState.update { state ->
                    state.copy(
                        senha = event.value,
                        erroMensage = null
                    )
                }
            }

            LoginUIEvent.OnNavegacaoRealizada -> {
                _uiState.update { state ->
                    state.copy(usuarioLogado = null)
                }
            }

            LoginUIEvent.OnEntrarClick -> {
                fazerLogin()
            }
        }
    }

    private fun fazerLogin() {
        val state = _uiState.value

        if (state.usuario.isBlank() || state.senha.isBlank()) {
            _uiState.update {
                it.copy(
                    erroMensage = "Preencha todos os campos"
                )
            }
            return
        }

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    erroMensage = null,
                    usuarioLogado = null
                )
            }

            val result = repository.login(
                usuario = state.usuario.trim(),
                senha = state.senha.trim()
            )

            result
                .onSuccess { usuarioLogado ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            erroMensage = null,
                            usuarioLogado = usuarioLogado
                        )
                    }
                }
                .onFailure { throwable ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            erroMensage = throwable.message ?: "Erro ao fazer Login"
                        )
                    }
                }
        }
    }
}