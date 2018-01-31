package moj.memes.base.viewslice

import android.arch.lifecycle.Lifecycle
import android.content.Context
import android.content.res.Resources
import android.view.View
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewSlice : ViewSlice, LayoutContainer {

    protected lateinit var context: Context
    protected lateinit var resources: Resources

    override lateinit var containerView: View

    override fun init(lifecycle: Lifecycle, view: View) {
        lifecycle.addObserver(this)
        context = view.context
        resources = view.resources
        containerView = view
    }
}
