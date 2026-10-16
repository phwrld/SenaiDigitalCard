package com.pdrinyo.thesenaidigitalcard.feature.aluno.carteirinha.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.pdrinyo.thesenaidigitalcard.feature.aluno.carteirinha.presentation.component.ValueText

@Composable
fun InfoAluno(textLabel: String, textValue: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        LabelText(
            text = "$textLabel: "
        )

        ValueText(
            text = textValue
        )
    }
}