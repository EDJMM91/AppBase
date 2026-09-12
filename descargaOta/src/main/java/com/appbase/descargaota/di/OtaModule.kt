package com.appbase.descargaota.di

import com.appbase.descargaota.OtaManager
import com.appbase.descargaota.service.OtaFirestoreService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OtaModule {

    @Singleton
    @Provides
    fun provideOtaManager(otaFirestoreService: OtaFirestoreService): OtaManager {
        return OtaManager()
    }

    @Singleton
    @Provides
    fun provideOtaFirestoreService(): OtaFirestoreService {
        return OtaFirestoreService()
    }
}
