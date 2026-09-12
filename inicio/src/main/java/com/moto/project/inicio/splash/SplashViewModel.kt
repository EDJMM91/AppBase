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
    private val _splashState = MutableStateFlow<SplashState>(SplashState.Cargando)
    val splashState: StateFlow<SplashState> = _splashState.asStateFlow()

    private val _titulo = MutableStateFlow("AppBase")
    val titulo: StateFlow<String> = _titulo.asStateFlow()

    private val _subtitulo = MutableStateFlow("Cargando...")
    val subtitulo: StateFlow<String> = _subtitulo.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000L)
            _splashState.value = SplashState.Listo
        }
    }

    fun updateTitle(title: String) {
        _titulo.value = title
    }

    fun updateSubtitle(subtitle: String) {
        _subtitulo.value = subtitle
    }
}

sealed class SplashState {
    object Cargando : SplashState()
    object Listo : SplashState()
    data class Error(val mensaje: String) : SplashState()
}
