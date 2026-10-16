package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.component

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
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.model.UnidadeCurricular

@Composable
fun UnidadeCurricularCard(
    modifier: Modifier = Modifier,
    uc: UnidadeCurricular,
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
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(IconContainerBlue, shape = RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = uc.nome,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextBlue
                )
                Text(
                    text = "Turma: ${uc.turma}",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Text(
                    text = "Carga Horária: ${uc.cargaHoraria} (${uc.totalAulas} Aulas)",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Dias: ${uc.diasSemana}",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = DarkTextBlue
                )
            }
        }
    }
}