package com.opgg.chai.ui.main

import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.opgg.chai.R
import com.opgg.chai.databinding.ActivityMainBinding
import com.opgg.chai.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initialize() {
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()
        navController?.let {
            binding.navBottomView.setupWithNavController(it)
            it.addOnDestinationChangedListener { _, destination, _ ->
                binding.navBottomView.visibility = when(destination.id) {
                    R.id.homeFragment,
                    R.id.battleFragment,
                    R.id.rankFragment,
                    R.id.rankInSchoolFragment,
                    R.id.settingFragment -> View.VISIBLE
                    else -> View.GONE
                }
            }
        }
    }
}