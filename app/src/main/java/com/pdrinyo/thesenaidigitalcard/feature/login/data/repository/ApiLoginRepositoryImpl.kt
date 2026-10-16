package com.pdrinyo.thesenaidigitalcard.feature.login.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.dto.ErrorResponseDto
import com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.dto.LoginRequestDto
import com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.service.AuthApi
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.PerfilAcesso
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.repository.LoginRepository
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class ApiLoginRepositoryImpl(private val api: AuthApi) : LoginRepository {
    private val errorJson = Json { ignoreUnknownKeys = true }

    override suspend fun login(usuario: String, senha: String): Result<UsuarioLogado> =
        runCatching {
            val response = api.login(LoginRequestDto(login = usuario, senha = senha))
            val token = response.token?.takeIf { it.isNotBlank() }
                ?: throw IllegalStateException("A API não retornou token de autenticação.")
            UsuarioLogado(
                id = response.id,
                nome = response.nome,
                matricula = response.matricula,
                perfil = PerfilAcesso.fromApi(response.perfil ?: response.tipo ?: "ALUNO"),
                curso = response.curso,
                turma = response.turma,
                token = token
            )
        }.recoverCatching { throw mapError(it) }

    private fun mapError(error: Throwable): Throwable = when (error) {
        is HttpException -> if (error.code() == 401) {
            IllegalArgumentException("Login ou senha inválidos.")
        } else {
            val bodyMessage = error.response()?.errorBody()?.string()?.let { body ->
                runCatching { errorJson.decodeFromString<ErrorResponseDto>(body).message }.getOrNull()
            }
            IllegalStateException(bodyMessage ?: "Erro no servidor (${error.code()}).")
        }
        is IOException -> IllegalStateException("Não foi possível conectar à API. Verifique se o servidor está rodando.")
        else -> error
    }
}
