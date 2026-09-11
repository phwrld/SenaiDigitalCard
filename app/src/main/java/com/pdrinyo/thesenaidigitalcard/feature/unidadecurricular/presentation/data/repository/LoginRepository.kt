package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.home.domain.UsuarioLogado


interface LoginRepository {
    suspend fun login( usuario:String, senha:String): Result<UsuarioLogado>
}