package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.component

import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.feature.login.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.feature.login.IconContainerBlue
import com.pdrinyo.thesenaidigitalcard.feature.login.InputBackground
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno

@Composable
fun UnidadeCurricularAlunoCard(
    modifier: Modifier = Modifier,
    item: UnidadeCurricularAluno
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(128.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = InputBackground),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(IconContainerBlue, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) { }
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = item.nome,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextBlue
                )
                Text(
                    text = "Professor: ${item.professor}",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Nota 1: ${item.nota1}",
                        fontSize = 12.sp,
                        color = DarkTextBlue
                    )
                    Text(
                        text = "Nota 2: ${item.nota2}",
                        fontSize = 12.sp,
                        color = DarkTextBlue
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Média: ${item.media}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (item.media >= 6.0) Color(0xFF2E7D32) else Color(0xFFC62828)
                    )
                    Text(
                        text = "Faltas: ${item.faltas}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
