package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.BackgroundBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.LightTextBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.InputBackground
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.UcProfessorViewModel

@Composable
fun UnidadeCurricularScreen(viewModel: UcProfessorViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.uiState.collectAsState()
    Column(modifier.fillMaxSize().background(BackgroundBlue).padding(horizontal = 20.dp, vertical = 20.dp)) {
        Text("Unidades curriculares", fontSize = 30.sp, color = DarkTextBlue, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text("Disciplinas e turmas em que você leciona", color = LightTextBlue, fontSize = 14.sp)
        Spacer(Modifier.height(20.dp))
        when {
            state.loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { CircularProgressIndicator(color = DarkTextBlue) }
            state.erro != null -> Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Não foi possível carregar os dados.", color = Color.White, fontWeight = FontWeight.SemiBold)
                Text(state.erro ?: "", color = Color.White)
                Spacer(Modifier.height(12.dp))
                Button(onClick = viewModel::carregar) { Text("Tentar novamente") }
            }
            state.itens.isEmpty() -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Nenhum registro vinculado ao professor.", color = Color.White) }
            else -> LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), contentPadding = PaddingValues(bottom = 24.dp)) {
                items(state.itens, key = { it.id }) { item ->
                    Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = InputBackground), modifier = Modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)) {
                        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                            Text(item.nome, color = DarkTextBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Text("Turma ${item.turma} • ${item.cargaHoraria}h", color = DarkTextBlue, fontSize = 14.sp)
                            Text("${item.aulasRealizadas}/${item.quantidadeAulas} aulas • ${item.diasSemana.joinToString(", ")}", color = Color.DarkGray, fontSize = 13.sp)
                            Text("${item.horarioInicio} às ${item.horarioFim}", color = LightTextBlue, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}
