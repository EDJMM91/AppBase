package com.moto.project.basededatos

import com.moto.project.perfil.model.ProfileModel

fun ProfileModel.toEntity(): com.moto.project.basededatos.entity.UserEntity {
    return com.moto.project.basededatos.entity.UserEntity(
        id = id,
        name = name,
        email = email,
        photoUrl = photoUrl,
        createdAt = System.currentTimeMillis()
    )
}

fun com.moto.project.basededatos.entity.UserEntity.toProfileModel(): ProfileModel {
    return ProfileModel(
        id = id,
        name = name,
        email = email,
        photoUrl = photoUrl,
        isActive = true
    )
}

data class PerfilCollection(
    val userId: String,
    val displayName: String,
    val isActive: Boolean
)
