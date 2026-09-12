package com.moto.project.basededatos.dao

import androidx.room.*
import com.moto.project.basededatos.entity.TemaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TemaDao {
    @Query("SELECT * FROM theme_colors WHERE key = :themeKey")
    suspend fun getThemeColor(themeKey: String): TemaEntity?

    @Query("SELECT * FROM theme_colors")
    fun getAllThemeColors(): Flow<List<TemaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThemeColor(entity: TemaEntity)
}
