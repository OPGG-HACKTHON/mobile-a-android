package com.opgg.chai.ui.base

import androidx.lifecycle.Lifecycle

class BaseLifecycleOwner {
    private val callbacks = mutableListOf<BaseLifecycleCallback>()
    private var lastEvent = Lifecycle.Event.ON_CREATE

    fun register(callback: BaseLifecycleCallback) {
        callback.apply(lastEvent)
        callbacks.add(callback)
    }

    fun unregister(callback: BaseLifecycleCallback) {
        callbacks.remove(callback)
    }

    fun notifyEvent(event: Lifecycle.Event) {
        callbacks.forEach { it.apply(event) }
    }
}