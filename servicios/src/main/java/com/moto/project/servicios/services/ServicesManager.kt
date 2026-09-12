package com.moto.project.servicios.services

import com.moto.project.nube.NUBE
import com.moto.project.autenticacion.AUTENTICACION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServicesManager @Inject constructor(
    private val cloudManager: NUBE,
    private val authManager: AUTENTICACION,
    private val firebaseAuth: FirebaseAuth,
    private val messaging: FirebaseMessaging
) {
    private val _serviceState = MutableStateFlow<ServiceState>(ServiceState.Ready)
    val serviceState: Flow<ServiceState> = _serviceState

    suspend fun initializeServices(): Result<Unit> {
        return try {
            val token = messaging.token.await()
            _serviceState.value = ServiceState.Ready
            Result.success(Unit)
        } catch (e: Exception) {
            _serviceState.value = ServiceState.Error(e.message ?: "Service init failed")
            Result.failure(e)
        }
    }

    suspend fun sendPermissionToCloud(permission: String, granted: Boolean) {
        cloudManager.ENVIAR_PERMISOS(permission, granted)
    }

    suspend fun refreshAuthToken(): Result<String> {
        return try {
            firebaseAuth.currentUser?.getIdToken(true)?.await()?.token
                ?.let { Result.success(it) }
                ?: Result.failure(Exception("No user authenticated"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

enum class ServiceState {
    Ready,
    Initializing,
    Error(String? = null)
}
