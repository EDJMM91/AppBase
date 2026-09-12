package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.DashboardDao
import com.moto.project.basededatos.entity.DashboardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class REPOSITORIO_DASHBOARD @Inject constructor(
    private val dashboardDao: DashboardDao
) {
    val allDashboardItems: Flow<List<DashboardEntity>> = dashboardDao.getAllItems()

    suspend fun getDashboardById(id: String): DashboardEntity? = dashboardDao.getAllItems().firstOrNull { it.id == id }
    suspend fun insertDashboardItem(item: DashboardEntity) = dashboardDao.insertItem(item)
    suspend fun deleteDashboardItem(item: DashboardEntity) = dashboardDao.deleteItem(item)
}