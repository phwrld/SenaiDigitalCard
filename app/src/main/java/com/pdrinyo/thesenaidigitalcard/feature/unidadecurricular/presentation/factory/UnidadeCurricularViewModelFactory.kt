package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.repository.UnidadeCurricularRepository
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.UnidadeCurricularViewModel

class UnidadeCurricularViewModelFactory(
    private val repository: UnidadeCurricularRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UnidadeCurricularViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UnidadeCurricularViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel desconhecido: ${modelClass.name}")
    }
}
