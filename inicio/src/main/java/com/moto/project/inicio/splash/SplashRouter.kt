package com.moto.project.inicio.splash

class SplashRouter {
    fun determinarDestino(estaAutenticado: Boolean): String {
        return if (estaAutenticado) "dashboard" else "login"
    }
}
