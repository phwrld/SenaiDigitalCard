package com.pdrinyo.thesenaidigitalcard.App.Navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pdrinyo.thesenaidigitalcard.App.TheSenaiDigitalCard
import com.pdrinyo.thesenaidigitalcard.feature.home.HomeProfessorScreen
import com.pdrinyo.thesenaidigitalcard.feature.home.HomeScreen
import com.pdrinyo.thesenaidigitalcard.feature.login.LoginScreen
import com.pdrinyo.thesenaidigitalcard.feature.turma.presentation.TurmasScreen
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.screen.UnidadeCurricularAlunoScreen
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel
) {
    val usuarioLogado by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {
        // LOGIN
        composable(Routes.Login.route) {
            LoginScreen(
                navController = navController,
                onLoginSucesso = { usuario ->
                    sessionViewModel.setUsuarioLogado(usuario)

                    // Define se é professor com base no nome ou regra de negócio
                    val ehProfessor = usuario.nome.contains("maria", ignoreCase = true)

                    val destino = if (ehProfessor) {
                        Routes.HomeProfessor.route
                    } else {
                        Routes.HomeAluno.route
                    }

                    navController.navigate(destino) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                    }
                }
            )
        }

        // HOME DO ALUNO
        composable(Routes.HomeAluno.route) {
            val usuario = usuarioLogado

            if (usuario == null) {
                LaunchedEffect(Unit) {
                    navController.navigate(Routes.Login.route) {
                        popUpTo(Routes.HomeAluno.route) { inclusive = true }
                    }
                }
            } else {
                HomeScreen(navController = navController)
            }
        }

        // CARTEIRINHA DO ALUNO
        composable(Routes.Carteirinha.route) {
            TheSenaiDigitalCard()
        }

        // UCS DO ALUNO
        composable(Routes.UnidadeCurricularAluno.route) {
            UnidadeCurricularAlunoScreen()
        }

        // HOME DO PROFESSOR
        composable(Routes.HomeProfessor.route) {
            HomeProfessorScreen(navController = navController)
        }

        // TURMAS DO PROFESSOR
        composable(Routes.Turmas.route) {
            TurmasScreen()
        }

        // UCS DO PROFESSOR
        composable(Routes.UnidadeCurricular.route) {
            UnidadeCurricularScreen()
        }
    }
}