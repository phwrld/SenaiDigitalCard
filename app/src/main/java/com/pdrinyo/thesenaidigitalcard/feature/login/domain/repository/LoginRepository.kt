package com.pdrinyo.thesenaidigitalcard.feature.login.domain.repository

import com.pdrinyo.thesenaidigitalcard.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login(usuario: String, senha: String): Result<UsuarioLogado>
}
