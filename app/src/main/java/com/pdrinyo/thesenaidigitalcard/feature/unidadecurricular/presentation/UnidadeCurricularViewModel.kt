package com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.domain.model.UnidadeCurricularAluno
import com.pdrinyo.thesenaidigitalcard.feature.unidadecurricular.presentation.data.repository.UnidadeCurricularRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class UnidadeCurricularUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricularAluno> = emptyList(),
    val errorMessage: String? = null
)

class UnidadeCurricularViewModel(
    private val repository: UnidadeCurricularRepository = UnidadeCurricularRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(UnidadeCurricularUiState())
    val uiState: StateFlow<UnidadeCurricularUiState> = _uiState.asStateFlow()

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            repository.listar()
                .onSuccess { unidades ->
                    _uiState.update {
                        it.copy(isLoading = false, unidades = unidades, errorMessage = null)
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = error.message ?: "Erro ao carregar UCs.")
                    }
                }
        }
    }
}
