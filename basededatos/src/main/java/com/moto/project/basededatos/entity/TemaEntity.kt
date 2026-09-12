package com.moto.project.basededatos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme_colors")
data class TemaEntity(
    @PrimaryKey val key: String,
    val colorValue: Long,
    val createdAt: Long = System.currentTimeMillis()
)
