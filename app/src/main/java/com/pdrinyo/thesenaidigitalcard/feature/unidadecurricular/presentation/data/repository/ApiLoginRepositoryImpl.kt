package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.ErrorResponseDto
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.LoginRequestDTO
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.service.AuthApi
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class ApiLoginRepositoryImpl(
    private val api: AuthApi
) : LoginRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun login(usuario: String, senha: String): Result<UsuarioLogado> {
        return runCatching {
            val response = api.login(LoginRequestDTO(usuario = usuario, senha = senha))
            UsuarioLogado(
                id = response.id ?: "",
                nome = response.nome ?: "",
                curso = response.curso ?: "",
                turma = response.turma ?: "",
                token = response.token ?: "",
                tipo = response.tipo ?: "ALUNO"
            )
        }.recoverCatching { throwable ->
            // Em vez de dar throw, oResult.failure trata o erro corretamente
            Result.failure<UsuarioLogado>(mapToDomainError(throwable)).getOrThrow()
        }
    }

    private fun mapToDomainError(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> mapHttpException(throwable)
            is IOException -> IllegalStateException(
                "Não foi possível conectar à API local. Verifique se ela está rodando."
            )
            else -> IllegalStateException(throwable.message ?: "Erro ao fazer login.")
        }
    }

    private fun mapHttpException(exception: HttpException): Throwable {
        if (exception.code() == 401) {
            return IllegalArgumentException("Login ou senha inválidos")
        }

        val messageFromBody = exception.response()?.errorBody()?.string()?.let { body ->
            runCatching {
                json.decodeFromString<ErrorResponseDto>(body).message
            }.getOrNull()
        }

        return IllegalStateException(messageFromBody ?: "Erro no servidor (${exception.code()}).")
    }
}