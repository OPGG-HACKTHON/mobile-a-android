package com.opgg.chai.di

import com.opgg.chai.ui.main.battle.BattleResultViewModel
import com.opgg.chai.ui.main.battle.BattleSearchViewModel
import com.opgg.chai.ui.main.battle.BattleViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object BattleModule {
    @Provides
    fun provideBattleViewModel() = BattleViewModel()

    @Provides
    fun provideBattleResultViewModel() = BattleResultViewModel()

    @Provides
    fun provideBattleSearchViewModel() = BattleSearchViewModel()
}
