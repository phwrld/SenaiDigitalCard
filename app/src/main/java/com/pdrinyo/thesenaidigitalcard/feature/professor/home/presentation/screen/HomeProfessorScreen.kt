package com.pdrinyo.thesenaidigitalcard.feature.professor.home.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.R
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.BackgroundBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.IconContainerBlue
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.InputBackground
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.LightTextBlue

@Composable
fun HomeProfessorScreen(
    onTurmasClick: () -> Unit,
    onUnidadesCurricularesClick: () -> Unit,
    modifier: Modifier = Modifier,
    profileImageRes: Int = R.drawable.homelanderr
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundBlue)
            .padding(horizontal = 28.dp, vertical = 40.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = DarkTextBlue)) {
                        append("Portal do ")
                    }
                    withStyle(style = SpanStyle(color = LightTextBlue)) {
                        append("Professor")
                    }
                },
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = profileImageRes),
                contentDescription = "Foto do Professor",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(190.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.White, CircleShape)
            )

            Spacer(modifier = Modifier.height(40.dp))

            HomeProfessorOptionButton(
                title = "Turmas",
                subtitle = null,
                onClick = {
                    onTurmasClick()
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeProfessorOptionButton(
                title = "UCS",
                subtitle = "(Inserir Faltas aos alunos)",
                onClick = {
                    onUnidadesCurricularesClick()
                }
            )
        }
    }
}

@Composable
fun HomeProfessorOptionButton(
    title: String,
    subtitle: String?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = InputBackground
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkTextBlue
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = DarkTextBlue
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = IconContainerBlue,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

