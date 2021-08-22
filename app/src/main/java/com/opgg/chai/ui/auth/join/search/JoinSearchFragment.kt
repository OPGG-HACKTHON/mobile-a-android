package com.opgg.chai.ui.auth.join.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinSearchBinding
import com.opgg.chai.ui.base.BaseFragment


class JoinSearchFragment : BaseFragment<FragmentJoinSearchBinding, JoinSearchViewModel>() {
    override var viewModel: JoinSearchViewModel = JoinSearchViewModel()
    override val layoutRes: Int = R.layout.fragment_join_search
}