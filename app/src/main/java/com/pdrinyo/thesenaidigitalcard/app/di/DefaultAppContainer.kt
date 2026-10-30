package com.pdrinyo.thesenaidigitalcard.app.di

import com.pdrinyo.thesenaidigitalcard.core.auth.SessionTokenStore
import com.pdrinyo.thesenaidigitalcard.core.network.NetworkClient
import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.data.remote.service.UnidadeCurricularApi
import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.pdrinyo.thesenaidigitalcard.feature.aluno.unidadescurriculares.domain.repository.UnidadeCurricularRepository
import com.pdrinyo.thesenaidigitalcard.feature.login.data.remote.service.AuthApi
import com.pdrinyo.thesenaidigitalcard.feature.login.data.repository.ApiLoginRepositoryImpl
import com.pdrinyo.thesenaidigitalcard.feature.login.domain.repository.LoginRepository

class DefaultAppContainer : AppContainer {
    override val sessionTokenStore: SessionTokenStore = SessionTokenStore()
    private val networkClient = NetworkClient(
        baseUrl = BASE_URL,
        sessionTokenStore = sessionTokenStore
    )

    private val authApi: AuthApi by lazy {
        networkClient.createPublic(AuthApi::class.java)
    }

    private val unidadeCurricularApi: UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(UnidadeCurricularApi::class.java)
    }

    override val loginRepository: LoginRepository by lazy {
        ApiLoginRepositoryImpl(api = authApi)
    }
    override val unidadeCurricularRepository: UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(api = unidadeCurricularApi)
    }

    override val turmaProfessorRepository by lazy {
        com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.repository.ApiTurmaProfessorRepository(
            networkClient.createAuthenticated(com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.data.remote.service.TurmaProfessorApi::class.java)
        )
    }
    override val ucProfessorRepository by lazy {
        com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.repository.ApiUcProfessorRepository(
            networkClient.createAuthenticated(com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.data.remote.service.UcProfessorApi::class.java)
        )
    }

    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
    }
}