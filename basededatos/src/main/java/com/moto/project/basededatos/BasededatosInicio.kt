package com.moto.project.basededatos

import com.moto.project.inicio.splash.SplashState
import com.moto.project.inicio.splash.SplashState.Cargando
import com.moto.project.inicio.splash.SplashState.Listo

fun SplashState.toCollection(): InicioCollection {
    return InicioCollection(
        state = this::class.simpleName ?: "Unknown",
        isReady = this == Listo
    )
}

data class InicioCollection(
    val state: String,
    val isReady: Boolean
)
