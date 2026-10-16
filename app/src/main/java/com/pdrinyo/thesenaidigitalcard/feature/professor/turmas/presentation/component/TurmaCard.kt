package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.IconContainerBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.InputBackground
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.model.Turma

@Composable
fun TurmaCard(
    modifier: Modifier = Modifier,
    turma: Turma,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = InputBackground),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = turma.nome,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextBlue
                )
                Text(
                    text = "Curso: ${turma.curso}",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "${turma.semestre} • ${turma.horario}",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Qtd. Alunos: ${turma.quantidadeAlunos}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkTextBlue
                )
            }
        }
    }
}