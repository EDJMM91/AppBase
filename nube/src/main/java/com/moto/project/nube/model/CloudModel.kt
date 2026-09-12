package com.moto.project.nube.model

data class CloudConfig(
    val featureEnabled: Boolean = false,
    val configValue: String = "",
    val lastUpdated: Long = 0L,
    val appVersion: String = "1.0"
)
