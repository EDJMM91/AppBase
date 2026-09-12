package com.moto.project.configuracion.config

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfig @Inject constructor() {
    var appName: String = "AppBase"
    var versionCode: Int = 1
    var versionName: String = "1.0"
    var isDebug: Boolean = true
    var baseUrl: String = "https://api.appbase.com"
    var cacheExpirationMs: Long = 3600000L

    companion object {
        const val CONFIG_PREFS_NAME = "app_config"
    }
}
