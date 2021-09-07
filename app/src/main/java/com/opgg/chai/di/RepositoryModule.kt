package com.opgg.chai.di

import com.opgg.chai.model.remote.ApiService
import com.opgg.chai.model.remote.BattleService
import com.opgg.chai.model.repository.BattleRepository
import com.opgg.chai.model.repository.LoLRepository
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
    fun provideBattleRepository(battleService: BattleService) = BattleRepository(battleService)
}
