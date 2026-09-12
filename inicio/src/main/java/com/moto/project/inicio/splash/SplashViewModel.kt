package com.moto.project.inicio.splash

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

@Singleton
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _splashState = MutableStateFlow(SplashState.Cargando)
    val splashState: StateFlow<SplashState> = _splashState.asStateFlow()
    init { viewModelScope.launch { delay(2000L); _splashState.value = SplashState.Listo } }
}

sealed class SplashState {
    object Cargando : SplashState()
    object Listo : SplashState()
    data class Error(val mensaje: String) : SplashState()
}
