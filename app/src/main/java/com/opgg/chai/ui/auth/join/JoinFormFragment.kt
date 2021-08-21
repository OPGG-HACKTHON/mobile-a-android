package com.opgg.chai.ui.auth.join

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.opgg.chai.R
import com.opgg.chai.databinding.FragmentJoinFormBinding
import com.opgg.chai.ui.base.BaseFragment

class JoinFormFragment : BaseFragment<FragmentJoinFormBinding, JoinFormViewModel>() {
    override var viewModel: JoinFormViewModel
        get() = JoinFormViewModel()
        set(value) {}
    override val layoutRes: Int
        get() = R.layout.fragment_join_form
}