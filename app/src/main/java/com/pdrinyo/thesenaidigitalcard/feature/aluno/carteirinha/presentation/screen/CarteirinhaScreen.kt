package com.pdrinyo.thesenaidigitalcard.feature.aluno.carteirinha.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pdrinyo.thesenaidigitalcard.R
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.aluno.carteirinha.presentation.component.PerfilAluno
import com.pdrinyo.thesenaidigitalcard.feature.aluno.carteirinha.presentation.component.QrCode

@Composable
fun CarteirinhaScreen(
    modifier: Modifier = Modifier,
    usuarioLogado: UsuarioLogado? = null
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.fundooooq),
            contentDescription = "Fundo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.4f),
                        Color.Black.copy(alpha = 0.7f),
                        Color(0xFF1A1A2E).copy(alpha = 0.9f)
                    )
                )
            )
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.senailogo),
                contentDescription = "Logo SENAI",
                modifier = Modifier.size(120.dp).padding(top = 20.dp).shadow(8.dp, CircleShape)
            )
            Spacer(modifier = Modifier.height(30.dp))
            PerfilAluno(
                nome = usuarioLogado?.nome ?: "Pedrinyo",
                curso = usuarioLogado?.curso ?: "System Developer",
                matricula = usuarioLogado?.matricula,
                turma = usuarioLogado?.turma,
                idFoto = R.drawable.homelanderr
            )
            Spacer(modifier = Modifier.height(28.dp))
            if (usuarioLogado == null) {
                // Apenas o Preview do Android Studio utiliza a imagem original.
                Image(
                    painter = painterResource(id = R.drawable.qrcodeee),
                    contentDescription = "QR Code de demonstracao",
                    modifier = Modifier.size(180.dp)
                )
            } else if (usuarioLogado.matricula.isNotBlank()) {
                // Na sessao real, o QR representa a matricula devolvida pela API.
                QrCode(
                    conteudo = usuarioLogado.matricula,
                    modifier = Modifier.size(180.dp)
                )
            } else {
                androidx.compose.material3.Text(
                    text = "QR indisponível: matrícula não informada",
                    color = Color.White
                )
            }
        }
    }
}
