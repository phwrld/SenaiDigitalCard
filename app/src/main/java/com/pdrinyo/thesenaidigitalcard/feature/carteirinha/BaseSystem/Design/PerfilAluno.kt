package com.pdrinyo.thesenaidigitalcard.feature.carteirinha.BaseSystem.Design

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pdrinyo.thesenaidigitalcard.R

@Composable
fun PerfilAluno(
    nome: String,
    curso: String,
    idFoto: Int = R.drawable.homelanderr,
    matricula: String? = null,
    turma: String? = null
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = idFoto),
            contentDescription = "Foto aluno",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(3.dp, Color(0xFFFFD700).copy(alpha = 0.6f), CircleShape)
                .shadow(16.dp, CircleShape)
        )
        InfoAluno(textLabel = "Nome: ", textValue = nome)
        InfoAluno(textLabel = "Curso: ", textValue = curso)
        if (!matricula.isNullOrBlank()) {
            InfoAluno(textLabel = "Matrícula: ", textValue = matricula)
        }
        if (!turma.isNullOrBlank()) {
            InfoAluno(textLabel = "Turma: ", textValue = turma)
        }
    }
}
