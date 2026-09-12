package com.moto.project.configuracion.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {

    @Singleton
    @Provides
    fun provideAppConfig(): com.moto.project.configuracion.config.AppConfig =
        com.moto.project.configuracion.config.AppConfig()
}
