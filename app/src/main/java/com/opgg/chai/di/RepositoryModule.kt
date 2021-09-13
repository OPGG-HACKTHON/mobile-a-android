package com.opgg.chai.di

import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.model.remote.SettingService
import com.opgg.chai.model.repository.LoLRepository
import com.opgg.chai.model.repository.SettingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLolRepository(
        apiService: ApiService): LoLRepository {
        return LoLRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideSettingRepository(settingService: SettingService) = SettingRepository(settingService)
}