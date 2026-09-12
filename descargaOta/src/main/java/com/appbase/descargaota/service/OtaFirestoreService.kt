package com.appbase.descargaota

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtaFirestoreService @Inject constructor() {

    private val firestore = FirebaseFirestore.getInstance()

    fun getOtaUpdates(): Flow<List<OtaUpdate>> {
        return flow {
            val snapshot = firestore.collection("ota_updates").get().await()
            val updates = snapshot.documents.map { doc ->
                OtaUpdate(
                    id = doc.id,
                    version = doc.getString("version") ?: "1.0",
                    changelog = doc.getString("changelog") ?: "",
                    isMandatory = doc.getBoolean("mandatory") ?: false,
                    downloadUrl = doc.getString("downloadUrl") ?: "",
                    sizeBytes = doc.getLong("sizeBytes") ?: 0L,
                    minSdk = doc.getLong("minSdk") ?: 26,
                    releaseDate = doc.getDate("releaseDate")?.time ?: 0L
                )
            }
            emit(updates)
        }
    }

    suspend fun registerDownload(version: String, status: String) {
        firestore.collection("ota_downloads").add(
            mapOf(
                "version" to version,
                "status" to status,
                "timestamp" to System.currentTimeMillis()
            )
        )
    }

    suspend fun getLatestOta(): OtaUpdate? {
        val snapshot = firestore.collection("ota_updates").document("latest").get().await()
        return OtaUpdate(
            id = snapshot.id,
            version = snapshot.getString("version") ?: "1.0",
            changelog = snapshot.getString("changelog") ?: "",
            isMandatory = snapshot.getBoolean("mandatory") ?: false,
            downloadUrl = snapshot.getString("downloadUrl") ?: "",
            sizeBytes = snapshot.getLong("sizeBytes") ?: 0L,
            minSdk = snapshot.getLong("minSdk") ?: 26,
            releaseDate = snapshot.getDate("releaseDate")?.time ?: 0L
        )
    }
}

data class OtaUpdate(
    val id: String = "",
    val version: String = "1.0",
    val changelog: String = "",
    val isMandatory: Boolean = false,
    val downloadUrl: String = "",
    val sizeBytes: Long = 0L,
    val minSdk: Long = 26,
    val releaseDate: Long = 0L
)
