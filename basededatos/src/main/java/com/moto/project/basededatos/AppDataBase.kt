package com.moto.project.basededatos

import androidx.room.Database
import androidx.room.RoomDatabase
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

@Database(
    entities = [UserEntity::class, ConfigEntity::class, DashboardEntity::class, PerfilEntity::class, TemaEntity::class, InicioEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun configDao(): ConfigDao
    abstract fun dashboardDao(): DashboardDao
    abstract fun perfilDao(): PerfilDao
    abstract fun temaDao(): TemaDao
    abstract fun inicioDao(): InicioDao
}
