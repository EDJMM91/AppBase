package com.moto.project.basededatos.repository

import com.moto.project.basededatos.dao.InicioDao
import com.moto.project.basededatos.entity.InicioEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class REPOSITORIO_INICIO @Inject constructor(
    private val inicioDao: InicioDao
) {
    val allInicioStates: Flow<List<InicioEntity>> = inicioDao.getAllStates()

    suspend fun getInicioState(id: String): InicioEntity? = inicioDao.getStateById(id)
    suspend fun insertInicioState(state: InicioEntity) = inicioDao.insertState(state)
}