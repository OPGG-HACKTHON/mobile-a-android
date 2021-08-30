package com.opgg.chai.ui.auth.join.form

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
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
        viewModel.navController = navController
        arrayAdapter = ArrayAdapter<String>(requireContext(), R.layout.item_spinner_school, resources.getStringArray(R.array.belong_list))
        binding.belongSpinner.adapter = arrayAdapter

        binding.belongEdt.setOnClickListener {
            val bundle = bundleOf("division" to binding.belongSpinner.selectedItem.toString())
            navController.navigate(R.id.action_joinFormFragment_to_joinSearchFragment, bundle)
        }
        viewModel.lolName.observe(viewLifecycleOwner, {
            if(it.length > 0) activateJoinButton()
        })

        getSelectSchoolInfo()
    }

    //JoinSearch에서 선택한 학교 정보를 가져옴
    fun getSelectSchoolInfo() {
        navController.currentBackStackEntry?.savedStateHandle?.get<String>("id")?.let { viewModel.schoolId = it }
        navController.currentBackStackEntry?.savedStateHandle?.get<String>("name")?.let {
            viewModel.schoolName.value = it
            activateJoinButton()
        }
    }

    fun activateJoinButton() {
        if(!viewModel.isAllContainValue()) return
        binding.joinFormBtn.isEnabled = true

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.joinFormBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.fill_blue, null))
        } else {
            binding.joinFormBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.fill_blue))
        }
    }
}