package moj.memes.base.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { action(it) } })
}