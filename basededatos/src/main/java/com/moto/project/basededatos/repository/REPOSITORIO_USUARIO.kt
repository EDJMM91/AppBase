package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.UserDao
import com.moto.project.basededatos.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class REPOSITORIO_USUARIO @Inject constructor(
    private val userDao: UserDao
) {
    val allUsers: Flow<List<UserEntity>> = userDao.getAllUsers()

    suspend fun getUserById(id: String): UserEntity? = userDao.getUserById(id)
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun insertAllUsers(users: List<UserEntity>) = userDao.insertAllUsers(users)
    suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)
}