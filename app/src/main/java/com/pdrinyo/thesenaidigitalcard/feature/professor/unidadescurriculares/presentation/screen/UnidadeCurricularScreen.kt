package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.screen

import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.component.AlunoFaltaCard
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.BackgroundBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.LightTextBlue
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.model.Aluno


@Composable
fun UnidadeCurricularScreen(
    modifier: Modifier = Modifier
) {
    val alunosList = remember {
        mutableStateListOf(
            Aluno("1", "Ana Silva", 0),
            Aluno("2", "Bruno Santos", 1),
            Aluno("3", "Carlos Eduardo", 0),
            Aluno("4", "Daniela Rocha", 2),
            Aluno("5", "Gabriel Lima", 0),
            Aluno("6", "Isabela Costa", 3),
            Aluno("7", "Lucas Martins", 1),
            Aluno("8", "Mariana Alves", 0)
        )
    }

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
                    withStyle(style = SpanStyle(color = DarkTextBlue)) {
                        append("U")
                    }
                    withStyle(style = SpanStyle(color = LightTextBlue)) {
                        append("Cs")
                    }
                },
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text("Demonstração local: faltas não são enviadas à API.", color = Color.White, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(12.dp))

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
                    items(alunosList, key = { it.id }) { aluno ->
                        AlunoFaltaCard(
                            aluno = aluno,
                            onAddFalta = {
                                val index = alunosList.indexOf(aluno)
                                if (index != -1) {
                                    alunosList[index] = aluno.copy(faltas = aluno.faltas + 1)
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnidadeCurricularScreenPreview() {
    UnidadeCurricularScreen()
}