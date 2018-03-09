package moj.memes.app.view

import android.content.Context
import android.content.Intent
import moj.memes.base.view.ScreenRouter
import moj.memes.base.view.ScreenRouter.Screen
import moj.memes.detail.MemesDetailActivity

class ScreenRouterImpl : ScreenRouter {

    override fun getScreenIntent(context: Context, screen: Screen): Intent? {
        val c: Class<*>? = when (screen) {
            ScreenRouter.Screen.Detail -> MemesDetailActivity::class.java
            ScreenRouter.Screen.List -> null // TODO
        }
        return if (c == null) null else Intent(context, c)
    }
}
