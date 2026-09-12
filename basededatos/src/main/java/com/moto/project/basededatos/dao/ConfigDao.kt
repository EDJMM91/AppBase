package com.moto.project.basededatos.dao

import androidx.room.*
import com.moto.project.basededatos.entity.ConfigEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConfigDao {
    @Query("SELECT * FROM app_config WHERE key = :key")
    suspend fun getConfigByKey(key: String): ConfigEntity?

    @Query("SELECT * FROM app_config")
    fun getAllConfig(): Flow<List<ConfigEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConfig(config: ConfigEntity)

    @Update
    suspend fun updateConfig(config: ConfigEntity)
}
