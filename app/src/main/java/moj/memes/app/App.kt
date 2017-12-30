package moj.memes.app

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import moj.memes.app.injection.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject lateinit var loggingTree: Timber.Tree
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this).build().inject(this)
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