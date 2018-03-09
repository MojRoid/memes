package moj.memes.base.view

import android.content.Context
import android.content.Intent

interface ScreenRouter {

    sealed class Screen {
        object Detail : Screen()
        object List : Screen()
    }

    fun getScreenIntent(context: Context, screen: Screen): Intent?
}