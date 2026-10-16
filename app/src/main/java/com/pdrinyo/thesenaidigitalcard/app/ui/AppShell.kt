package com.pdrinyo.thesenaidigitalcard.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.BackgroundBlue
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado

/**
 * Barra de navegação discreta, nas cores do projeto original.
 * As telas preservam sua própria composição, imagens e cartões.
 */
@Composable
fun AppShell(
    title: String,
    usuarioLogado: UsuarioLogado,
    canNavigateBack: Boolean,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onNavigateBack: () -> Unit,
    onLogout: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        containerColor = BackgroundBlue,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().background(BackgroundBlue).padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (canNavigateBack) {
                    TextButton(onClick = onNavigateBack) { Text("Voltar", color = Color.White) }
                } else {
                    Text(usuarioLogado.nome, color = Color.White, modifier = Modifier.padding(12.dp))
                }
                TextButton(onClick = onLogout) { Text("Sair", color = Color.White) }
            }
        }
    ) { padding -> content(padding) }
}
