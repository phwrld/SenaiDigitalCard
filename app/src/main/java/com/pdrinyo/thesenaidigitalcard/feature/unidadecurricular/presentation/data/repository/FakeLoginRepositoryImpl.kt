package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado

class FakeLoginRepositoryImpl : LoginRepository {

    override suspend fun login(usuario: String, senha: String): Result<UsuarioLogado> {
        return when {
            // 1. LOGIN PH -> Redireciona para ALUNO SCREEN
            usuario.equals("PH", ignoreCase = true) && senha == "1234" -> {
                Result.success(
                    UsuarioLogado(
                        id = "1",
                        nome = "Aluno PH",
                        curso = "Desenvolvimento de Sistemas",
                        turma = "DS101", // Turma diferente de "Professor" -> vai para HomeAluno
                        token = "fake-token-ph"
                    )
                )
            }
            // 2. LOGIN PROFESSOR -> Redireciona para HOME PROFESSOR SCREEN
            usuario.equals("PROFESSOR", ignoreCase = true) && senha == "1234" -> {
                Result.success(
                    UsuarioLogado(
                        id = "2",
                        nome = "Professor Teste",
                        curso = "Engenharia de Software",
                        turma = "Professor", // Turma = "Professor" -> o AppNavHost direciona para HomeProfessor
                        token = "fake-token-professor"
                    )
                )
            }
            else -> {
                Result.failure(IllegalArgumentException("Login ou senha inválidos"))
            }
        }
    }
}