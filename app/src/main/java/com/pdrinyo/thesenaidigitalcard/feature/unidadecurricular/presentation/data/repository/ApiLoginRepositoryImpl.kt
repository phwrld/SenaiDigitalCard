package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.ErrorResponseDto
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.LoginRequestDTO
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.service.AuthApi
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class ApiLoginRepositoryImpl(private val api: AuthApi) : LoginRepository {
    private val errorJson = Json { ignoreUnknownKeys = true }

    override suspend fun login(usuario: String, senha: String): Result<UsuarioLogado> =
        runCatching {
            // LoginRequestDTO usa @SerialName("login"), compativel com Rafael.
            val response = api.login(LoginRequestDTO(usuario = usuario, senha = senha))
            val token = response.token?.takeIf { it.isNotBlank() }
                ?: throw IllegalStateException("A API nao retornou um token de autenticacao.")
            UsuarioLogado(
                id = response.id,
                nome = response.nome,
                matricula = response.matricula,
                curso = response.curso,
                turma = response.turma,
                token = token,
                tipo = response.tipo?.uppercase() ?: "ALUNO"
            )
        }.recoverCatching { throw mapToDomainError(it) }

    private fun mapToDomainError(error: Throwable): Throwable = when (error) {
        is HttpException -> {
            if (error.code() == 401) {
                IllegalArgumentException("Login ou senha inválidos.")
            } else {
                val message = error.response()?.errorBody()?.string()?.let { body ->
                    runCatching { errorJson.decodeFromString<ErrorResponseDto>(body).message }.getOrNull()
                }
                IllegalStateException(message ?: "Erro no servidor (${error.code()}).")
            }
        }
        is IOException -> IllegalStateException(
            "Não foi possível conectar à API. Verifique se o servidor está rodando."
        )
        else -> error
    }
}
