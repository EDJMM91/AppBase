package com.appbase.descargaota.model

data class OtaConfig(
    val checkIntervalMs: Long = 3600000L,
    val autoDownload: Boolean = false,
    val wifiOnly: Boolean = true,
    val notifyMandatory: Boolean = true,
    val baseUrl: String = "https://firebasestorage.googleapis.com",
    val collectionPath: String = "ota_updates",
    val minSdkVersion: Int = 26
)
