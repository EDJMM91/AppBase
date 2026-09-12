package com.moto.project.basededatos.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.moto.project.basededatos.AppDataBase
import com.moto.project.basededatos.repository.AppRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "appbase_database.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppRepository(database: AppDataBase): AppRepository {
        return AppRepository(
            database.userDao(),
            database.configDao(),
            database.dashboardDao(),
            database.perfilDao(),
            database.temaDao(),
            database.inicioDao()
        )
    }
}
