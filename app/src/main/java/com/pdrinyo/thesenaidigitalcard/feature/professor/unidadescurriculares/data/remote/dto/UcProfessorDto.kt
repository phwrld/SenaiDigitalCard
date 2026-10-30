package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UcProfessorDto(val id: Int, val nome: String, val turmaId: Int, val turma: String, val cargaHoraria: Int, val quantidadeAulas: Int, val aulasRealizadas: Int, val diasSemana: List<String>, val horarioInicio: String, val horarioFim: String)
