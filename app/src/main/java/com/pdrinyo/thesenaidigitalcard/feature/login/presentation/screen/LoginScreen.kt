package com.pdrinyo.thesenaidigitalcard.feature.login.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.login.presentation.LoginEvent
import com.pdrinyo.thesenaidigitalcard.feature.login.presentation.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    onLoginSucesso: (UsuarioLogado) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(uiState.usuarioLogado) {
        uiState.usuarioLogado?.let { usuario ->
            viewModel.onEvent(LoginEvent.OnNavegacaoRealizada)
            onLoginSucesso(usuario)
        }
    }
    LoginContent(uiState = uiState, onEvent = viewModel::onEvent, modifier = modifier)
}
