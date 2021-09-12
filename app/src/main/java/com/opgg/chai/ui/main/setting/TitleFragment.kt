package com.opgg.chai.ui.main.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentTitleBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TitleFragment : BaseFragment<FragmentTitleBinding, TitleViewModel>() {
    @Inject override lateinit var viewModel: TitleViewModel
    override val layoutRes: Int = R.layout.fragment_title
    private val historyAdapter: TitleHistoryAdapter = TitleHistoryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.view = this
        binding.viewmodel = viewModel

        initLayout()

        return view
    }

    fun moveBack(view: View) { navController.popBackStack() }

    fun initLayout() {
        binding.titleHistoryList.adapter = historyAdapter
    }
}