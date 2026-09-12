package com.moto.project.theme.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.moto.project.theme.Primary
import com.moto.project.theme.Secondary
import com.moto.project.theme.Background
import com.moto.project.theme.Surface
import com.moto.project.theme.OnPrimary
import com.moto.project.theme.OnSecondary
import com.moto.project.theme.OnBackground
import com.moto.project.theme.OnSurface

@Module
@InstallIn(SingletonComponent::class)
object ThemeModule {
    @Singleton
    @Provides
    fun providePrimary(): com.moto.project.theme.Primary = com.moto.project.theme.Primary
}
