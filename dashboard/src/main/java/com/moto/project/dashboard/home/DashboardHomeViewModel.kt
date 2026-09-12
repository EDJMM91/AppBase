package com.moto.project.dashboard.home

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardHomeViewModel @Inject constructor() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    fun cargarDatos() { _uiState.update { it.copy(estaCargando = false, datosCargados = true) } }
    fun cerrarSesion() { _uiState.update { it.copy(estaAutenticado = false) } }
}

data class DashboardUiState(val estaCargando: Boolean = true, val datosCargados: Boolean = false, val titulo: String = "", val estaAutenticado: Boolean = true)
