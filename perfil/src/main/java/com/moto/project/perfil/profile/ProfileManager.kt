package com.moto.project.perfil.profile

import com.moto.project.basededatos.repository.AppRepository
import com.moto.project.perfil.model.ProfileModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileManager @Inject constructor(
    private val repository: AppRepository
) {
    val profileData: Flow<ProfileModel> = repository.allUsers.map { users ->
        users.firstOrNull()?.let { user ->
            ProfileModel(
                id = user.id,
                name = user.name,
                email = user.email,
                photoUrl = user.photoUrl,
                isActive = true
            )
        } ?: ProfileModel()
    }

    suspend fun updateProfile(profile: ProfileModel): Result<Unit> {
        return try {
            val entity = com.moto.project.basededatos.entity.UserEntity(
                id = profile.id,
                name = profile.name,
                email = profile.email,
                photoUrl = profile.photoUrl,
                createdAt = System.currentTimeMillis()
            )
            repository.insertUser(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
