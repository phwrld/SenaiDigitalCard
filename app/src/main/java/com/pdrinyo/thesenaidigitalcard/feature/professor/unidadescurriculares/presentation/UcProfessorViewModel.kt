package com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdrinyo.thesenaidigitalcard.feature.professor.unidadescurriculares.domain.repository.UcProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UcProfessorViewModel(private val repository: UcProfessorRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UcProfessorUiState())
    val uiState = _uiState.asStateFlow()
    init { carregar() }
    fun carregar() {
        viewModelScope.launch {
            _uiState.value = UcProfessorUiState(loading = true)
            repository.listar().fold(
                onSuccess = { _uiState.value = UcProfessorUiState(loading = false, itens = it) },
                onFailure = { _uiState.value = UcProfessorUiState(loading = false, erro = it.message ?: "Falha de comunicação com a API.") }
            )
        }
    }
}
