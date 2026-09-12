package com.moto.project.basededatos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inicio_states")
data class InicioEntity(
    @PrimaryKey val id: String,
    val state: String,
    val isReady: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
