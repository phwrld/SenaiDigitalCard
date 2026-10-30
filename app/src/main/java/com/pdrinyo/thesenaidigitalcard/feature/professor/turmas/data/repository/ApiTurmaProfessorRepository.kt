package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.service.TurmaProfessorApi
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.dto.TurmaProfessorDto
import kotlinx.coroutines.CancellationException

class ApiTurmaProfessorRepository(private val api: TurmaProfessorApi) : TurmaProfessorRepository {
    override suspend fun listar(): Result<List<TurmaProfessorDto>> = try {
        Result.success(api.listar())
    } catch (e: CancellationException) { throw e }
      catch (e: Exception) { Result.failure(e) }
}
