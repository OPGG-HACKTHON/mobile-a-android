package com.opgg.chai.ui.auth.join.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinSearchBinding
import com.opgg.chai.model.data.auth.SchoolInfo
import com.opgg.chai.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class JoinSearchFragment : BaseFragment<FragmentJoinSearchBinding, JoinSearchViewModel>(), JoinSearchItemListener {
    @Inject override lateinit var viewModel: JoinSearchViewModel
    override val layoutRes: Int = R.layout.fragment_join_search
    private val adapter = JoinSearchAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        viewModel.navController = navController

        initLayout()

        binding.vm = viewModel

        return view
    }

    fun initLayout() {
        binding.searchList.adapter = adapter

        setDivisionType()
        setKeyEvent()
    }

    //editText의 Enter key 이벤트 캐치 후, 검색
    fun setKeyEvent() {
        binding.searchSchoolEdt.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER) {
                val word = binding.searchSchoolEdt.text.toString()
                if(word.length > 0) viewModel.searchSchool(word)
                binding.searchSchoolEdt.setText("")
            }
            true
        }
    }

    // 가입 폼에서 초등학교/중학교/고등학교 선택 정보를 받아오는 메소드
    fun setDivisionType() {
        arguments?.let {
            viewModel.division = it.getString("division") ?: resources.getStringArray(R.array.belong_list)[0]
        }
    }

    // RecyclerView 상에서 아이템 선택 시, 이전 페이지로 전환
    override fun seletedItem(item: SchoolInfo) {
        val bundle = bundleOf("id" to item.id, "name" to item.name)
        navController.previousBackStackEntry?.savedStateHandle?.apply {
            set("id", item.id)
            set("name", item.name)
        }

        navController.popBackStack()
    }
}