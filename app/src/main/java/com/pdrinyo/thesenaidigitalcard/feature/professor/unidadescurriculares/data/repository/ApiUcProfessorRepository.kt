package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.service.UcProfessorApi
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.repository.UcProfessorRepository
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.dto.UcProfessorDto
import kotlinx.coroutines.CancellationException

class ApiUcProfessorRepository(private val api: UcProfessorApi) : UcProfessorRepository {
    override suspend fun listar(): Result<List<UcProfessorDto>> = try {
        Result.success(api.listar())
    } catch (e: CancellationException) { throw e }
      catch (e: Exception) { Result.failure(e) }
}
