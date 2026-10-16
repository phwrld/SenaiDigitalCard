package com.pdrinyo.thesenaidigitalcard.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.pdrinyo.thesenaidigitalcard.app.di.AppContainer
import com.pdrinyo.thesenaidigitalcard.app.navigation.AppNavHost
import com.pdrinyo.thesenaidigitalcard.core.designsystem.theme.TheSenaiDigitalCardTheme

@Composable
fun App(container: AppContainer) {

    val systemDarkTheme = isSystemInDarkTheme()
    var darkTheme by rememberSaveable { mutableStateOf(systemDarkTheme) }

    TheSenaiDigitalCardTheme(
        darkTheme = darkTheme,
        dynamicColor = false
    ) {
        val navController = rememberNavController()

        AppNavHost(
            navController = navController,
            darkTheme = darkTheme,
            onDarkThemeChange = { darkTheme = it },
            container = container
        )
    }
}