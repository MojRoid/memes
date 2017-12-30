package moj.memes.base.view.widget

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.view.View
import kotlinx.android.extensions.LayoutContainer

interface Widget : LifecycleObserver {
    fun init(lifecycle: Lifecycle, view: View)
}