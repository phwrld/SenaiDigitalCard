package com.pdrinyo.thesenaidigitalcard.App.Navegation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import com.pdrinyo.thesenaidigitalcard.App.TheSenaiDigitalCard
import com.pdrinyo.thesenaidigitalcard.feature.home.HomeProfessorScreen
import com.pdrinyo.thesenaidigitalcard.feature.home.HomeScreen
import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.login.DarkTextBlue
import com.pdrinyo.thesenaidigitalcard.feature.login.LoginScreen
import com.pdrinyo.thesenaidigitalcard.feature.turma.presentation.TurmasScreen
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.screen.UnidadeCurricularAlunoScreen
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.UnidadeCurricularViewModel
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.factory.UnidadeCurricularViewModelFactory
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.network.NetworkFactory
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.screen.UnidadeCurricularScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    sessionViewModel: SessionViewModel
) {
    val usuario by sessionViewModel.usuarioLogado.collectAsStateWithLifecycle()

    fun logout() {
        sessionViewModel.limparSession()
        navController.navigate(Routes.Login.route) {
            popUpTo(navController.graph.id) { inclusive = true }
            launchSingleTop = true
        }
    }

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            LoginScreen(
                onLoginSucesso = { logado ->
                    sessionViewModel.setUsuarioLogado(logado)
                    val destino = if (logado.tipo.equals("PROFESSOR", ignoreCase = true)) {
                        Routes.HomeProfessor.route
                    } else {
                        Routes.HomeAluno.route
                    }
                    navController.navigate(destino) {
                        popUpTo(Routes.Login.route) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.HomeAluno.route) {
            if (usuario == null) RedirecionarParaLogin(navController)
            else Box {
                HomeScreen(navController = navController)
                SairButton(onClick = ::logout, modifier = Modifier.align(Alignment.TopEnd))
            }
        }

        composable(Routes.Carteirinha.route) {
            if (usuario == null) RedirecionarParaLogin(navController)
            else {
                TheSenaiDigitalCard(usuarioLogado = usuario)
            }
        }

        composable(Routes.UnidadeCurricularAluno.route) {
            if (usuario == null) RedirecionarParaLogin(navController)
            else {
                val factory = remember {
                    UnidadeCurricularViewModelFactory(
                        ApiUnidadeCurricularRepositoryImpl(
                            NetworkFactory.createUnidadeCurricularApi()
                        )
                    )
                }
                val ucViewModel: UnidadeCurricularViewModel = viewModel(factory = factory)
                UnidadeCurricularAlunoScreen(viewModel = ucViewModel)
            }
        }

        composable(Routes.HomeProfessor.route) {
            if (!usuario.ehProfessor()) RedirecionarParaLogin(navController)
            else Box {
                HomeProfessorScreen(navController = navController)
                SairButton(onClick = ::logout, modifier = Modifier.align(Alignment.TopEnd))
            }
        }

        // As telas abaixo preservam o design existente, mas exigem permissao de
        // professor. A API de referencia nao oferece endpoints de turmas/faltas.
        composable(Routes.Turmas.route) {
            if (!usuario.ehProfessor()) RedirecionarParaLogin(navController)
            else TurmasScreen()
        }

        composable(Routes.UnidadeCurricular.route) {
            if (!usuario.ehProfessor()) RedirecionarParaLogin(navController)
            else UnidadeCurricularScreen()
        }
    }
}

private fun UsuarioLogado?.ehProfessor(): Boolean =
    this?.tipo.equals("PROFESSOR", ignoreCase = true)

@Composable
private fun SairButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    TextButton(onClick = onClick, modifier = modifier.padding(8.dp)) {
        Text("Sair", color = DarkTextBlue)
    }
}

@Composable
private fun RedirecionarParaLogin(navController: NavHostController) {
    LaunchedEffect(Unit) {
        navController.navigate(Routes.Login.route) {
            popUpTo(navController.graph.id) { inclusive = true }
            launchSingleTop = true
        }
    }
}
