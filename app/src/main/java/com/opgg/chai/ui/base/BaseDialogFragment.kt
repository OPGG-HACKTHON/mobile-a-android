package com.opgg.chai.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

abstract class BaseDialogFragment<VB : ViewDataBinding, VM : ViewModel> : DialogFragment() {
    protected lateinit var binding: VB
    protected abstract var viewModel: VM
    protected lateinit var navController: NavController

    @get:LayoutRes
    protected abstract val layoutRes: Int

    private val lifecycleOwner = BaseLifecycleOwner()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = this.findNavController()

        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = this

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