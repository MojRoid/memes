package moj.memes.app.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import moj.memes.app.App
import moj.memes.base.injection.BaseModule
import moj.memes.base.injection.scopes.PerApplication
import moj.memes.base.network.injection.NetworkModule

@PerApplication
@Component(modules = [
    AndroidInjectionModule::class,
    Bindings::class,
    BaseModule::class,
    NetworkModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: App)
}