package com.opgg.chai.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentConfirmBinding
import com.opgg.chai.ui.base.BaseDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmFragment : BaseDialogFragment<FragmentConfirmBinding, ConfirmViewModel>() {
    @Inject override lateinit var viewModel: ConfirmViewModel
    override val layoutRes: Int = R.layout.fragment_confirm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding.viewmodel = viewModel
        binding.view = this

        initLayout()

        return view
    }

    fun initLayout() {
        arguments?.let {
            val title = it.getString("title") ?: ""
            val contents = it.getString("contents") ?: ""

            viewModel.setDialogContents(title, contents)
        }
    }

    fun setResult(isConfirm: Boolean) {
        if(isConfirm) {
            navController.previousBackStackEntry?.savedStateHandle?.apply { set("isConfirm", isConfirm) }
        }

        dismiss()
    }

}