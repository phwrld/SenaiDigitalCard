package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.network.NetworkFactory
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.service.UnidadeCurricularApi
import retrofit2.HttpException
import java.io.IOException

class UnidadeCurricularRepository(
    private val api: UnidadeCurricularApi = NetworkFactory.createUnidadeCurricularApi()
) {
    suspend fun listar(): Result<List<UnidadeCurricularAluno>> = runCatching {
        api.listar().map { it.toDomain() }
    }.recoverCatching { error ->
        throw when (error) {
            is HttpException -> if (error.code() == 401) {
                IllegalStateException("Sessão expirada. Faça login novamente.")
            } else {
                IllegalStateException("Erro ao carregar UCs (${error.code()}).")
            }
            is IOException -> IllegalStateException("Não foi possível conectar à API.")
            else -> error
        }
    }
}
