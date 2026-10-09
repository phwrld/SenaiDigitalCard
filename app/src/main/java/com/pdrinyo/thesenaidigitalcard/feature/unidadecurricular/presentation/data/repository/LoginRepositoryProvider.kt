package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository

import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.network.NetworkFactory

/**
 * O login da aplicacao usa exclusivamente a API, sem usuarios de teste.
 */
object LoginRepositoryProvider {
    fun provide(): LoginRepository = ApiLoginRepositoryImpl(NetworkFactory.createAuthApi())
}
