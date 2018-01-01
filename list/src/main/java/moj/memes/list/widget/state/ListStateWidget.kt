package moj.memes.list.widget.state

import moj.memes.base.widget.Widget

interface ListStateWidget : Widget {

    fun showLoading()

    fun showContent()

    fun showError()
}