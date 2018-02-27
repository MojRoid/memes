package moj.memes.app

import android.os.StrictMode
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import moj.memes.app.injection.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

open class App : DaggerApplication() {

    @Inject lateinit var loggingTree: Timber.Tree

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        setupDebugTools()
    }

    private fun setupDebugTools() {
        initStrictMode()
        initTimber()
    }

    private fun initStrictMode() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyFlashScreen()
                .penaltyDeathOnNetwork()
                .build())

        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build())
    }

    private fun initTimber() {
        Timber.plant(loggingTree)
    }
}
