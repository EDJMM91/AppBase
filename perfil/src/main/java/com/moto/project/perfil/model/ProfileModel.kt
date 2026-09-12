package com.moto.project.perfil.model

data class ProfileModel(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val isActive: Boolean = false,
    val preferences: Map<String, Any> = emptyMap()
)
