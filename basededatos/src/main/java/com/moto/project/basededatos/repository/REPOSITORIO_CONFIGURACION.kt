package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.ConfigDao
import com.moto.project.basededatos.entity.ConfigEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class REPOSITORIO_CONFIGURACION @Inject constructor(
    private val configDao: ConfigDao
) {
    val allConfig: Flow<List<ConfigEntity>> = configDao.getAllConfig()

    suspend fun getConfigByKey(key: String): ConfigEntity? = configDao.getConfigByKey(key)
    suspend fun insertConfig(config: ConfigEntity) = configDao.insertConfig(config)
    suspend fun updateConfig(config: ConfigEntity) = configDao.updateConfig(config)
}