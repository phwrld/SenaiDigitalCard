package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.BackgroundBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.LightTextBlue
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.model.Turma
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation.component.TurmaCard

@Composable
fun TurmasScreen(
    modifier: Modifier = Modifier,
    turmas: List<Turma> = listOf(
        Turma("1", "Turma 1A", "Desenvolvimento de Sistemas", 32, "13:30 - 17:30", "1º Semestre"),
        Turma("2", "Turma 2B", "Análise de Sistemas", 28, "08:00 - 12:00", "2º Semestre"),
        Turma("3", "Turma 3C", "Redes de Computadores", 25, "19:00 - 22:30", "1º Semestre"),
        Turma("4", "Turma 4A", "Banco de Dados", 30, "13:30 - 17:30", "3º Semestre")
    )
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = DarkTextBlue)) { append("Tur") }
                    withStyle(style = SpanStyle(color = LightTextBlue)) { append("mas") }
                },
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color.White.copy(alpha = 0.85f), shape = RoundedCornerShape(24.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(24.dp))
                    .padding(12.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(turmas) { turma ->
                        TurmaCard(turma = turma)
                    }
                }
            }
        }
    }
}