package com.opgg.chai.ui.base

import androidx.lifecycle.Lifecycle

interface BaseLifecycleCallback {
    fun apply(event: Lifecycle.Event)
}