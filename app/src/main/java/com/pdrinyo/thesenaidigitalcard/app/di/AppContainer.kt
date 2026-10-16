package com.pdrinyo.thesenaidigitalcard.app.di

import com.pdrinyo.thesenaidigitalcard.core.auth.SessionTokenStore
import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.repository.LoginRepository

interface AppContainer {
    val sessionTokenStore: SessionTokenStore
    val loginRepository: LoginRepository
    val unidadeCurricularRepository: UnidadeCurricularRepository
}
