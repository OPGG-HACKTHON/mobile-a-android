package com.opgg.chai.ui.main

import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.opgg.chai.R
import com.opgg.chai.databinding.ActivityMainBinding
import com.opgg.chai.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initialize() {
        setStatusBarColor()
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

    private fun setStatusBarColor() {
        with(window) {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            statusBarColor = ContextCompat.getColor(context, R.color.white)

            if(Build.VERSION.SDK_INT >= 23) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

    }

}