package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.TemaDao
import com.moto.project.basededatos.entity.TemaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class REPOSITORIO_TEMA @Inject constructor(
    private val temaDao: TemaDao
) {
    val allThemeColors: Flow<List<TemaEntity>> = temaDao.getAllThemeColors()

    suspend fun getThemeColor(key: String): TemaEntity? = temaDao.getThemeColor(key)
    suspend fun insertThemeColor(entity: TemaEntity) = temaDao.insertThemeColor(entity)
}