package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.PerfilDao
import com.moto.project.basededatos.entity.PerfilEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class REPOSITORIO_PERFIL @Inject constructor(
    private val perfilDao: PerfilDao
) {
    val allProfiles: Flow<List<PerfilEntity>> = perfilDao.getAllProfiles()

    suspend fun getProfileById(id: String): PerfilEntity? = perfilDao.getProfileById(id)
    suspend fun insertProfile(profile: PerfilEntity) = perfilDao.insertProfile(profile)
    suspend fun updateProfile(profile: PerfilEntity) = perfilDao.updateProfile(profile)
    suspend fun deleteProfile(profile: PerfilEntity) = perfilDao.deleteProfile(profile)
}