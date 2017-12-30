package moj.memes.list.view.widget.state

import moj.memes.base.view.widget.Widget

interface ListStateWidget : Widget {

    fun showLoading()

    fun showContent()

    fun showError()
}