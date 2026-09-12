package com.moto.project.basededatos.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.moto.project.basededatos.BASE_DATOS
import com.moto.project.basededatos.dao.UserDao
import com.moto.project.basededatos.dao.ConfigDao
import com.moto.project.basededatos.dao.DashboardDao
import com.moto.project.basededatos.dao.PerfilDao
import com.moto.project.basededatos.dao.TemaDao
import com.moto.project.basededatos.dao.InicioDao
import com.moto.project.basededatos.repository.REPOSITORIO_USUARIO
import com.moto.project.basededatos.repository.REPOSITORIO_CONFIGURACION
import com.moto.project.basededatos.repository.REPOSITORIO_DASHBOARD
import com.moto.project.basededatos.repository.REPOSITORIO_PERFIL
import com.moto.project.basededatos.repository.REPOSITORIO_TEMA
import com.moto.project.basededatos.repository.REPOSITORIO_INICIO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BASE_DATOS {
        return Room.databaseBuilder(
            context,
            BASE_DATOS::class.java,
            "appbase_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUsuario(database: BASE_DATOS): REPOSITORIO_USUARIO {
        return REPOSITORIO_USUARIO(database.userDao())
    }

    @Singleton
    @Provides
    fun provideConfiguracion(database: BASE_DATOS): REPOSITORIO_CONFIGURACION {
        return REPOSITORIO_CONFIGURACION(database.configDao())
    }

    @Singleton
    @Provides
    fun provideDashboard(database: BASE_DATOS): REPOSITORIO_DASHBOARD {
        return REPOSITORIO_DASHBOARD(database.dashboardDao())
    }

    @Singleton
    @Provides
    fun providePerfil(database: BASE_DATOS): REPOSITORIO_PERFIL {
        return REPOSITORIO_PERFIL(database.perfilDao())
    }

    @Singleton
    @Provides
    fun provideTema(database: BASE_DATOS): REPOSITORIO_TEMA {
        return REPOSITORIO_TEMA(database.temaDao())
    }

    @Singleton
    @Provides
    fun provideInicio(database: BASE_DATOS): REPOSITORIO_INICIO {
        return REPOSITORIO_INICIO(database.inicioDao())
    }
}