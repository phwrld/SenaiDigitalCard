package com.pdrinyo.thesenaidigitalcard

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.LoginRequestDTO
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.dto.LoginResponseDto
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.data.remote.dto.UnidadeCurricularDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ApiContractTest {
    private val json = Json { ignoreUnknownKeys = true }

    @Test
    fun loginEnviaCampoLoginConformeApiDoProfessor() {
        val request = json.encodeToString(LoginRequestDTO(usuario = "aluno", senha = "1234"))
        assertTrue(request.contains("\"login\":\"aluno\""))
        assertFalse(request.contains("\"usuario\""))
    }

    @Test
    fun loginLeMatriculaEToken() {
        val payload = """{"id":"1","nome":"PH","matricula":"2026001","curso":"DS","turma":"2DEV","token":"abc","outroCampo":true}"""
        val response = json.decodeFromString<LoginResponseDto>(payload)
        assertEquals("2026001", response.matricula)
        assertEquals("abc", response.token)
    }

    @Test
    fun unidadesCurricularesSeguemContratoCompletoDaApi() {
        val payload = """[{"id":"uc1","nome":"Banco de Dados","professor":"Rafael","nota1":8.5,"nota2":7.0,"media":7.75,"faltas":2}]"""
        val response = json.decodeFromString<List<UnidadeCurricularDto>>(payload)
        val unidade = response.single().toDomain()
        assertEquals("Banco de Dados", unidade.nome)
        assertEquals("Rafael", unidade.professor)
        assertEquals(8.5, unidade.nota1, 0.01)
        assertEquals(7.0, unidade.nota2, 0.01)
        assertEquals(7.75, unidade.media, 0.01)
        assertEquals(2, unidade.faltas)
    }
}
