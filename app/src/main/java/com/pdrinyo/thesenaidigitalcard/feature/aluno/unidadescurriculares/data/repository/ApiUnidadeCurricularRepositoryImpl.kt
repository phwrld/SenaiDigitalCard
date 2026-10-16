package com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.data.remote.service.UnidadeCurricularApi
import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular
import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import retrofit2.HttpException
import java.io.IOException

/** Mesmo fluxo de Repository do Rafael: API -> DTO -> modelo -> ViewModel. */
class ApiUnidadeCurricularRepositoryImpl(
    private val api: UnidadeCurricularApi
) : UnidadeCurricularRepository {

    override suspend fun listar(): Result<List<UnidadeCurricular>> =
        runCatching {
            api.listar().map { it.toDomain() }
        }.recoverCatching { erro ->
            throw when (erro) {
                is HttpException -> if (erro.code() == 401) {
                    IllegalStateException("Sua sessão expirou. Faça login novamente.")
                } else {
                    IllegalStateException("Erro ao carregar unidades curriculares (${erro.code()}).")
                }
                is IOException -> IllegalStateException("Não foi possível conectar à API.")
                else -> IllegalStateException(erro.message ?: "Erro ao carregar unidades curriculares.")
            }
        }
}
