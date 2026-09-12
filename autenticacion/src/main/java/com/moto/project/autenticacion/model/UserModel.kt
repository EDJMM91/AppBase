package com.moto.project.autenticacion.model

data class UserModel(
    val id: String = "",
    val displayName: String = "",
    val email: String = "",
    val photoUrl: String? = null,
    val isAuthenticated: Boolean = false,
    val token: String? = null
)
