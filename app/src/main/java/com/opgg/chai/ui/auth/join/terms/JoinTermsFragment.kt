package com.opgg.chai.ui.auth.join.terms

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinBinding
import com.opgg.chai.model.data.auth.TermItem
import com.opgg.chai.ui.base.BaseFragment

class JoinTermsFragment : BaseFragment<FragmentJoinBinding, JoinTermsViewModel>() {
    val adapter = JoinTermsAdapter()

    override var viewModel: JoinTermsViewModel = JoinTermsViewModel(adapter)
    override val layoutRes: Int = R.layout.fragment_join

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        setJoinContents()

        adapter.isSelectAll.observe(viewLifecycleOwner, { isCheckAll ->
            binding.joinTermCheckAll.isChecked = isCheckAll
        })
        viewModel._isSelectAll.observe(viewLifecycleOwner, { isAll ->
            if(isAll) binding.joinTermsBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.fill_blue))
            else binding.joinTermsBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.fill_default))
        })
        viewModel.navController = navController

        binding.joinTermList.adapter = adapter
        binding.vm = viewModel

        return view
    }

    // todo : 서버에서 내려줄 지, 네이티브에 가지고 있을지에 따라 변경 가능
    private fun setJoinContents() {
        val terms = ArrayList<TermItem>()

        terms.add(TermItem("(필수) 서비스 이용약관", false, ""))
        terms.add(TermItem("(필수) 개인정보 수집 및 이용 동의", false, ""))
        terms.add(TermItem("(필수) 위치기반 서비스 이용약관", false, ""))

        adapter.addTerm(terms)
    }
}
