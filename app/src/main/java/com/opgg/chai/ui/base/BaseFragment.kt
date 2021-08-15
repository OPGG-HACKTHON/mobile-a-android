package com.opgg.chai.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: VB
    protected abstract var viewModel: VM

    @get:LayoutRes
    protected abstract val layoutRes: Int

    private val lifecycleOwner = BaseLifecycleOwner()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.executePendingBindings()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifyEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onResume() {
        super.onResume()
        notifyEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun onDestroy() {
        super.onDestroy()
        notifyEvent(Lifecycle.Event.ON_DESTROY)
    }

    protected fun register(callback: BaseLifecycleCallback) {
        lifecycleOwner.register(callback)
    }

    protected fun unregister(callback: BaseLifecycleCallback) {
        lifecycleOwner.unregister(callback)
    }

    private fun notifyEvent(event: Lifecycle.Event) {
        lifecycleOwner.notifyEvent(event)
    }
}