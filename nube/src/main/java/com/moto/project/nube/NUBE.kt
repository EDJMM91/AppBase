package com.moto.project.nube

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NUBE @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage,
    private val messaging: FirebaseMessaging,
    private val remoteConfig: FirebaseRemoteConfig
) {
    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Disconnected)
    val connectionState: Flow<ConnectionState> = _connectionState

    init {
        CONFIGURAR_REMOTO()
    }

    private fun CONFIGURAR_REMOTO() {
        val settings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    suspend fun ENVIAR_PERMISOS(permission: String, granted: Boolean): Result<Unit> {
        return try {
            firestore.collection("permissions").document(permission)
                .set(mapOf("granted" to granted, "timestamp" to System.currentTimeMillis()))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun GUARDAR_EN_NUBE(path: String, data: Map<String, Any>): Result<Unit> {
        return try {
            firestore.collection("data").document(path).set(data)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun <T> OBTENER_DE_NUBE(collection: String, document: String, clazz: Class<T>): Result<T?> {
        return try {
            val snapshot = firestore.collection(collection).document(document).get().await()
            Result.success(snapshot.toObject(clazz))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun SUBIR_ARCHIVO(filePath: String, uri: android.net.Uri): Result<String> {
        return try {
            val ref = storage.reference.child(filePath)
            val task = ref.putFile(uri).await()
            Result.success(task.storage.downloadUrl.await().toString())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

enum class ConnectionState {
    Disconnected,
    Connecting,
    Connected,
    Error(String? = null)
}