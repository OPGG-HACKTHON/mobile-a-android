package com.opgg.chai.ui.main.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
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
    private val titleAdapter = TitleItemAdapter()

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
        binding.titleList.adapter = titleAdapter
        binding.titleList.layoutManager = FlexboxLayoutManager(requireContext()).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
        }
    }
}