package com.moto.project.basededatos.dao

import androidx.room.*
import com.moto.project.basededatos.entity.PerfilEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PerfilDao {
    @Query("SELECT * FROM user_profiles WHERE id = :userId")
    suspend fun getProfileById(userId: String): PerfilEntity?

    @Query("SELECT * FROM user_profiles")
    fun getAllProfiles(): Flow<List<PerfilEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: PerfilEntity)

    @Update
    suspend fun updateProfile(profile: PerfilEntity)

    @Delete
    suspend fun deleteProfile(profile: PerfilEntity)
}
