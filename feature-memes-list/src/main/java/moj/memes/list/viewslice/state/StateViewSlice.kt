package moj.memes.list.viewslice.state

import moj.memes.base.viewslice.ViewSlice

interface StateViewSlice : ViewSlice {

    fun showLoading()

    fun showContent()

    fun showError()
}
