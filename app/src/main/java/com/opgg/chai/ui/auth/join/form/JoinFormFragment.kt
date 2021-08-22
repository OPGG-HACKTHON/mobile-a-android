package com.opgg.chai.ui.auth.join.form

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinFormBinding
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class JoinFormFragment : BaseFragment<FragmentJoinFormBinding, JoinFormViewModel>() {
    @Inject override lateinit var viewModel: JoinFormViewModel
    override val layoutRes: Int = R.layout.fragment_join_form
    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        initLayout()
        binding.vm = viewModel
        binding.lifecycleOwner = this

        return view
    }

    fun initLayout() {
        arrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.item_spinner_school, resources.getStringArray(R.array.belong_list))
        binding.belongSpinner.adapter = arrayAdapter

        binding.belongEdt.setOnClickListener { navController.navigate(R.id.action_joinFormFragment_to_joinSearchFragment) }
    }
}