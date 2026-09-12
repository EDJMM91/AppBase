package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.UserDao
import com.moto.project.basededatos.dao.ConfigDao
import com.moto.project.basededatos.dao.DashboardDao
import com.moto.project.basededatos.dao.PerfilDao
import com.moto.project.basededatos.dao.TemaDao
import com.moto.project.basededatos.dao.InicioDao
import com.moto.project.basededatos.entity.UserEntity
import com.moto.project.basededatos.entity.ConfigEntity
import com.moto.project.basededatos.entity.DashboardEntity
import com.moto.project.basededatos.entity.PerfilEntity
import com.moto.project.basededatos.entity.TemaEntity
import com.moto.project.basededatos.entity.InicioEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val userDao: UserDao,
    private val configDao: ConfigDao,
    private val dashboardDao: DashboardDao,
    private val perfilDao: PerfilDao,
    private val temaDao: TemaDao,
    private val inicioDao: InicioDao
) {
    val allUsers: Flow<List<UserEntity>> = userDao.getAllUsers()
    val allConfig: Flow<List<ConfigEntity>> = configDao.getAllConfig()
    val allDashboardItems: Flow<List<DashboardEntity>> = dashboardDao.getAllItems()
    val allProfiles: Flow<List<PerfilEntity>> = perfilDao.getAllProfiles()
    val allThemeColors: Flow<List<TemaEntity>> = temaDao.getAllThemeColors()
    val allInicioStates: Flow<List<InicioEntity>> = inicioDao.getAllStates()

    suspend fun getUserById(id: String): UserEntity? = userDao.getUserById(id)
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun insertAllUsers(users: List<UserEntity>) = userDao.insertAllUsers(users)
    suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)

    suspend fun getConfigByKey(key: String): ConfigEntity? = configDao.getConfigByKey(key)
    suspend fun insertConfig(config: ConfigEntity) = configDao.insertConfig(config)
    suspend fun updateConfig(config: ConfigEntity) = configDao.updateConfig(config)

    suspend fun getDashboardById(id: String): DashboardEntity? = dashboardDao.getAllItems().firstOrNull { it.id == id }
    suspend fun insertDashboardItem(item: DashboardEntity) = dashboardDao.insertItem(item)
    suspend fun deleteDashboardItem(item: DashboardEntity) = dashboardDao.deleteItem(item)

    suspend fun getProfileById(id: String): PerfilEntity? = perfilDao.getProfileById(id)
    suspend fun insertProfile(profile: PerfilEntity) = perfilDao.insertProfile(profile)
    suspend fun updateProfile(profile: PerfilEntity) = perfilDao.updateProfile(profile)
    suspend fun deleteProfile(profile: PerfilEntity) = perfilDao.deleteProfile(profile)

    suspend fun getThemeColor(key: String): TemaEntity? = temaDao.getThemeColor(key)
    suspend fun insertThemeColor(entity: TemaEntity) = temaDao.insertThemeColor(entity)

    suspend fun getInicioState(id: String): InicioEntity? = inicioDao.getStateById(id)
    suspend fun insertInicioState(state: InicioEntity) = inicioDao.insertState(state)
}
