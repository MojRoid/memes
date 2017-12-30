package moj.memes.base.extension

import android.app.Activity
import android.view.View
import moj.memes.base.view.BaseActivity
import moj.memes.base.view.widget.Widget

fun BaseActivity.initWidgets(vararg widgets: Widget) {
    widgets.forEach { it.init(this.lifecycle, getContentView()) }
}

fun Activity.getContentView(): View = this.findViewById(android.R.id.content)