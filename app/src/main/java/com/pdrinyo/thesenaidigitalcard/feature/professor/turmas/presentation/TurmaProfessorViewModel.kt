package com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdrinyo.thesenaidigitalcard.feature.professor.turmas.domain.repository.TurmaProfessorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TurmaProfessorViewModel(private val repository: TurmaProfessorRepository): ViewModel() {
    private val _uiState = MutableStateFlow(TurmaProfessorUiState())
    val uiState = _uiState.asStateFlow()
    init { carregar() }
    fun carregar() {
        viewModelScope.launch {
            _uiState.value = TurmaProfessorUiState(loading = true)
            repository.listar().fold(
                onSuccess = { _uiState.value = TurmaProfessorUiState(loading = false, itens = it) },
                onFailure = { _uiState.value = TurmaProfessorUiState(loading = false, erro = it.message ?: "Falha de comunicação com a API.") }
            )
        }
    }
}
