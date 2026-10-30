package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TurmaProfessorDto(val id: Int, val codigo: String, val curso: String, val semestre: String, val quantidadeAlunos: Int, val diasSemana: List<String>, val horarioInicio: String, val horarioFim: String)
