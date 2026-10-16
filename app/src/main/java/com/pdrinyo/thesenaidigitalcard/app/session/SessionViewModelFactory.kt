package com.pdrinyo.thesenaidigitalcard.app.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdrinyo.thesenaidigitalcard.core.auth.SessionTokenStore

class SessionViewModelFactory(
    private val sessionTokenStore : SessionTokenStore
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SessionViewModel(sessionTokenStore = sessionTokenStore) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}