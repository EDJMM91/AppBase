package com.moto.project.basededatos

import com.moto.project.configuracion.config.AppConfig

fun AppConfig.toEntity(): com.moto.project.basededatos.entity.ConfigEntity {
    return com.moto.project.basededatos.entity.ConfigEntity(
        key = "app_name",
        value = appName,
        updatedAt = System.currentTimeMillis()
    )
}

suspend fun saveConfig(config: AppConfig): com.moto.project.basededatos.entity.ConfigEntity {
    return config.toEntity()
}

data class ConfiguracionCollection(
    val appName: String,
    val versionCode: Int,
    val baseUrl: String
)
