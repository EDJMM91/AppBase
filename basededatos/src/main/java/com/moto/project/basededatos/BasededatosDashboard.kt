package com.moto.project.basededatos

import com.moto.project.dashboard.model.DashboardModel
import kotlinx.coroutines.flow.Flow

fun Flow<List<DashboardModel>>.filterActive(): Flow<List<DashboardModel>> {
    return this.filter { it.titulo.isNotEmpty() }
}

suspend fun insertDashboardItem(model: DashboardModel): DashboardModel {
    return model.copy(id = System.currentTimeMillis().toString())
}

data class DashboardCollection(
    val section: String,
    val items: List<DashboardModel>
)
