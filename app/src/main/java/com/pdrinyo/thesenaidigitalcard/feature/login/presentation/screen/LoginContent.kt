package com.pdrinyo.thesenaidigitalcard.feature.login.presentation.screen

import com.pdrinyo.thesenaidigitalcard.R
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdrinyo.thesenaidigitalcard.feature.login.presentation.LoginEvent
import com.pdrinyo.thesenaidigitalcard.feature.login.presentation.LoginUiState

/** Parte visual desacoplada do ViewModel, preservando a interface original. */
@Composable
fun LoginContent(
    uiState: LoginUiState,
    onEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier
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
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = DarkTextBlue)) {
                        append("Área do ")
                    }
                    withStyle(style = SpanStyle(color = LightTextBlue)) {
                        append("Aluno")
                    }
                },
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )

            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = uiState.usuario,
                onValueChange = { value ->
                    onEvent(LoginEvent.OnUsuarioChange(value))
                },
                placeholder = { Text("Usuário", color = Color.Gray) },
                singleLine = true,
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(IconContainerBlue, shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Login Icon",
                            tint = Color.White
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DarkTextBlue,
                    unfocusedTextColor = DarkTextBlue,
                    focusedContainerColor = InputBackground,
                    unfocusedContainerColor = InputBackground,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = uiState.senha,
                onValueChange = { value ->
                    onEvent(LoginEvent.OnSenhaChange(value))
                },
                placeholder = { Text("Senha", color = Color.Gray) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(IconContainerBlue, shape = RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Senha Icon",
                            tint = Color.White
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DarkTextBlue,
                    unfocusedTextColor = DarkTextBlue,
                    focusedContainerColor = InputBackground,
                    unfocusedContainerColor = InputBackground,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    onEvent(LoginEvent.OnEntrarClick)
                },
                enabled = !uiState.isLoading,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.5.dp
                    )
                } else {
                    Text(
                        text = "Entrar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            uiState.erroMensage?.let { erro ->
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = erro,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.tsdglogo),
                contentDescription = "Logo",
                modifier = Modifier.size(600.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    LoginContent(uiState = LoginUiState(), onEvent = {})
}
