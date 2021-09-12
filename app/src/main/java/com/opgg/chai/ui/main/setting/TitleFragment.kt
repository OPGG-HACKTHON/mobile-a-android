package com.opgg.chai.ui.main.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentTitleBinding
import com.opgg.chai.ui.base.BaseFragment
import javax.inject.Inject

class TitleFragment : BaseFragment<FragmentTitleBinding, TitleViewModel>() {
//    @Inject
    override var viewModel: TitleViewModel = TitleViewModel()
    override val layoutRes: Int = R.layout.fragment_title

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.view = this
        binding.viewmodel = viewModel

        return view
    }

    fun moveBack(view: View) { navController.popBackStack() }
}