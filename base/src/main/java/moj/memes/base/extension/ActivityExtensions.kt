package moj.memes.base.extension

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.view.View
import moj.memes.base.view.widget.Widget

fun AppCompatActivity.initWidgets(vararg widgets: Widget) {
    widgets.forEach { it.init(this.lifecycle, getContentView()) }
}

fun Activity.getContentView(): View = this.findViewById(android.R.id.content)