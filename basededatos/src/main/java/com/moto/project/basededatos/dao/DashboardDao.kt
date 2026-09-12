package com.moto.project.basededatos.dao

import androidx.room.*
import com.moto.project.basededatos.entity.DashboardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DashboardDao {
    @Query("SELECT * FROM dashboard_items")
    fun getAllItems(): Flow<List<DashboardEntity>>

    @Query("SELECT * FROM dashboard_items WHERE isActive = 1")
    fun getActiveItems(): Flow<List<DashboardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: DashboardEntity)

    @Delete
    suspend fun deleteItem(item: DashboardEntity)
}
