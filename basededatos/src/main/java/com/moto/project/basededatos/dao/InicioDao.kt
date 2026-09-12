package com.moto.project.basededatos.dao

import androidx.room.*
import com.moto.project.basededatos.entity.InicioEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InicioDao {
    @Query("SELECT * FROM inicio_states WHERE id = :stateId")
    suspend fun getStateById(stateId: String): InicioEntity?

    @Query("SELECT * FROM inicio_states")
    fun getAllStates(): Flow<List<InicioEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertState(state: InicioEntity)
}
