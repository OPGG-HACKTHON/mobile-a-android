package com.opgg.chai.ui.auth.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinBinding
import com.opgg.chai.ui.base.BaseFragment

class JoinFragment : BaseFragment<FragmentJoinBinding, JoinViewModel>() {
    override var viewModel: JoinViewModel = JoinViewModel()
    override val layoutRes: Int = R.layout.fragment_join

}