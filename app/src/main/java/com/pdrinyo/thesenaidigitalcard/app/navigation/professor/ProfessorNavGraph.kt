package com.pdrinyo.thesenaidigitalcard.app.navigation.professor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pdrinyo.thesenaidigitalcard.app.navigation.GraphRoutes
import com.pdrinyo.thesenaidigitalcard.app.navigation.ProfileGuard
import com.pdrinyo.thesenaidigitalcard.app.ui.AppShell
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.PerfilAcesso
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.professor.home.presentation.screen.HomeProfessorScreen
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation.screen.TurmasScreen
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.screen.UnidadeCurricularScreen
import kotlinx.coroutines.flow.StateFlow

fun NavGraphBuilder.professorNavGraph(
    navController: NavHostController,
    turmaRepository: com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.repository.TurmaProfessorRepository,
    ucRepository: com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.repository.UcProfessorRepository,
    usuarioLogadoFlow: StateFlow<UsuarioLogado?>,
    darkTheme: Boolean,
    onDarkThemeChange: (Boolean) -> Unit,
    onLogout: () -> Unit,
    onSessaoAusente: () -> Unit,
    onPerfilInvalido: (PerfilAcesso) -> Unit
) {
    navigation(startDestination = ProfessorRoutes.HOME, route = GraphRoutes.PROFESSOR) {
        composable(ProfessorRoutes.HOME) {
            ProfileGuard(usuarioLogadoFlow, PerfilAcesso.PROFESSOR, onSessaoAusente, onPerfilInvalido) { usuario ->
                AppShell("Início", usuario, false, darkTheme, onDarkThemeChange, {}, onLogout) { padding ->
                    HomeProfessorScreen(
                        onTurmasClick = { navController.navigate(ProfessorRoutes.TURMAS) },
                        onUnidadesCurricularesClick = { navController.navigate(ProfessorRoutes.UNIDADES_CURRICULARES) },
                        modifier = Modifier.fillMaxSize().padding(padding)
                    )
                }
            }
        }
        composable(ProfessorRoutes.TURMAS) {
            ProfileGuard(usuarioLogadoFlow, PerfilAcesso.PROFESSOR, onSessaoAusente, onPerfilInvalido) { usuario ->
                AppShell("Turmas", usuario, true, darkTheme, onDarkThemeChange, { navController.popBackStack() }, onLogout) { padding ->
                    TurmasScreen(viewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation.TurmaProfessorViewModel(turmaRepository) as T
                    }), modifier = Modifier.fillMaxSize().padding(padding))
                }
            }
        }
        composable(ProfessorRoutes.UNIDADES_CURRICULARES) {
            ProfileGuard(usuarioLogadoFlow, PerfilAcesso.PROFESSOR, onSessaoAusente, onPerfilInvalido) { usuario ->
                AppShell("Unidades Curriculares", usuario, true, darkTheme, onDarkThemeChange, { navController.popBackStack() }, onLogout) { padding ->
                    UnidadeCurricularScreen(viewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = object : androidx.lifecycle.ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T = com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation.UcProfessorViewModel(ucRepository) as T
                    }), modifier = Modifier.fillMaxSize().padding(padding))
                }
            }
        }
    }
}
