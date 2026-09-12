package com.moto.project.basededatos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class PerfilEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val photoUrl: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
