package com.moto.project.basededatos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dashboard_items")
data class DashboardEntity(
    @PrimaryKey val id: String,
    val titulo: String,
    val subtitulo: String = "",
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
