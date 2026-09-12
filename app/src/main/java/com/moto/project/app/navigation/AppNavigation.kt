package com.moto.project.app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.moto.project.autenticacion.auth.AuthenticationViewModel
import com.moto.project.nube.cloud.CloudViewModel
import com.moto.project.perfil.profile.ProfileViewModel
import com.moto.project.configuracion.config.ConfigViewModel
import com.moto.project.servicios.services.ServicesViewModel

@Composable
fun AppNavigation() {
    // El ensamblador dirige entre módulos sin lógica de negocio
    // Cada módulo expone sus vistas a través de interfaces
}
