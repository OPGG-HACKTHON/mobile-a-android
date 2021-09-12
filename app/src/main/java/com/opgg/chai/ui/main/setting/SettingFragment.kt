package com.opgg.chai.ui.main.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentSettingBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>() {
    @Inject
    override lateinit var viewModel: SettingViewModel
    override val layoutRes: Int = R.layout.fragment_setting

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmdoel = viewModel
        binding.view = this

        initLayout()

        return view
    }

    fun initLayout() {
        viewModel.isMoveHome.observe(viewLifecycleOwner, {
            if (it) {
                navController.navigate(R.id.loginFragment)
            }
        })

        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>("isConfirm")
            ?.observe(viewLifecycleOwner, {
                Log.d("result", "$it")
                if (it) viewModel.getDialogResult()
            })
    }

    fun confirmLogout(view: View) {
        val bundle = bundleOf(
            "title" to resources.getString(R.string.logout_title),
            "contents" to resources.getString(R.string.logout_contents),
        )
        viewModel.action = "logout"
        navController.navigate(R.id.confirmFragment, bundle)
    }

    fun confirmLeave(view: View) {
        val bundle = bundleOf(
            "title" to resources.getString(R.string.leave_title),
            "contents" to resources.getString(R.string.leave_contents),
        )
        viewModel.action = "leave"
        navController.navigate(R.id.confirmFragment, bundle)
    }

    fun moveTitle(view: View) {
        Log.d("setting", "movetitle")
        navController.navigate(R.id.action_settingFragment_to_titleFragment)
    }

}