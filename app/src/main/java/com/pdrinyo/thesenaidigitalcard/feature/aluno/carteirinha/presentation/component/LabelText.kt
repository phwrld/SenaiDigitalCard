package com.pdrinyo.thesenaidigitalcard.feature.aluno.carteirinha.presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun LabelText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        fontSize = 24.sp,
        color = Color(0xFFE0E0E0),
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.5.sp,
        modifier = modifier
    )
}