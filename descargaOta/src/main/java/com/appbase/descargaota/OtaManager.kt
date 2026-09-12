package com.appbase.descargaota

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OtaManager @Inject constructor() {

    suspend fun checkForUpdate(currentVersion: String): UpdateCheckResult {
        val latestVersion = fetchLatestVersionFromFirestore()
        val isUpdateAvailable = latestVersion != currentVersion
        val downloadUrl = if (isUpdateAvailable) {
            "https://firebasestorage.googleapis.com/v0/b/appbase-ota.appspot.com/o/releases%2Fappbase-$latestVersion.apk?alt=media"
        } else null

        return UpdateCheckResult(
            currentVersion = currentVersion,
            latestVersion = latestVersion,
            isUpdateAvailable = isUpdateAvailable,
            downloadUrl = downloadUrl,
            timestamp = System.currentTimeMillis()
        )
    }

    suspend fun downloadUpdate(version: String, context: Context): Boolean {
        val url = "https://firebasestorage.googleapis.com/v0/b/appbase-ota.appspot.com/o/releases%2Fappbase-$version.apk?alt=media"
        val request = android.app.DownloadManager.Request(Uri.parse(url))
            .setTitle("AppBase OTA")
            .setDescription("Descargando versión $version")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "appbase-$version.apk")
            .setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val reference = manager.enqueue(request)
        return reference != -1L
    }

    private suspend fun fetchLatestVersionFromFirestore(): String {
        // Conectado a Firebase Firestore
        // Colección: "ota_updates" / documento: "latest"
        return try {
            val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            val snapshot = firestore.collection("ota_updates").document("latest").get().await()
            snapshot.getString("version") ?: "1.0"
        } catch (e: Exception) {
            "1.0"
        }
    }
}

data class UpdateCheckResult(
    val currentVersion: String,
    val latestVersion: String,
    val isUpdateAvailable: Boolean,
    val downloadUrl: String?,
    val timestamp: Long
)
