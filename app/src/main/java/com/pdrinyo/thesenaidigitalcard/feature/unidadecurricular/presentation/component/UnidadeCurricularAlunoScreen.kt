package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.screen

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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdrinyo.thesenaidigitalcard.feature.login.BackgroundBlue
import com.pdrinyo.thesenaidigitalcard.feature.login.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.feature.login.LightTextBlue
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.UnidadeCurricularViewModel
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.component.UnidadeCurricularAlunoCard

@Composable
fun UnidadeCurricularAlunoScreen(
    viewModel: UnidadeCurricularViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.carregar()
    }

    Box(
        modifier = Modifier
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
                    withStyle(SpanStyle(color = DarkTextBlue)) { append("U") }
                    withStyle(SpanStyle(color = LightTextBlue)) { append("Cs") }
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
                    .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(24.dp))
                    .border(2.dp, Color.White, RoundedCornerShape(24.dp))
                    .padding(12.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    if (uiState.isLoading) {
                        item {
                            Box(Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
                                CircularProgressIndicator()
                            }
                        }
                    } else if (uiState.errorMessage != null) {
                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(uiState.errorMessage ?: "", color = DarkTextBlue)
                                Spacer(Modifier.height(12.dp))
                                Button(onClick = viewModel::carregar) {
                                    Text("Tentar novamente")
                                }
                            }
                        }
                    } else if (uiState.unidades.isEmpty()) {
                        item {
                            Text("Nenhuma unidade curricular encontrada.", color = DarkTextBlue)
                        }
                    } else {
                        items(uiState.unidades, key = { it.id }) { item ->
                            UnidadeCurricularAlunoCard(item = item)
                        }
                    }
                }
            }
        }
    }
}
