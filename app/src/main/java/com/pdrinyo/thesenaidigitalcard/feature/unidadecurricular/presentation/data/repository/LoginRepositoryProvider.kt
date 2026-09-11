package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository


import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.network.NetworkFactory

object LoginRepositoryProvider {
    private const val USE_FAKE_REPOSITORY = false

    fun provide(): LoginRepository {
        return if (USE_FAKE_REPOSITORY) {
            FakeLoginRepositoryImpl()
        } else {
            ApiLoginRepositoryImpl(NetworkFactory.createAuthApi())
        }
    }
}