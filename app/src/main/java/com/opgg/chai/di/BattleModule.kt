package com.opgg.chai.di

import com.opgg.chai.model.remote.BattleService
import com.opgg.chai.ui.main.battle.BattleResultViewModel
import com.opgg.chai.ui.main.battle.search.BattleSearchViewModel
import com.opgg.chai.ui.main.battle.BattleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import retrofit2.Retrofit

@Module
@InstallIn(FragmentComponent::class)
object BattleModule {
    @Provides
    fun provideBattleService(retrofit: Retrofit) = retrofit.create(BattleService::class.java)

    @Provides
    fun provideBattleViewModel() = BattleViewModel()

    @Provides
    fun provideBattleResultViewModel() = BattleResultViewModel()

    @Provides
    fun provideBattleSearchViewModel(battleService: BattleService) = BattleSearchViewModel(battleService)
}
