package moj.memes.app.injection

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import moj.memes.app.App
import moj.memes.base.injection.BaseModule
import moj.memes.base.injection.scopes.PerApplication
import moj.memes.base.network.injection.NetworkModule

@PerApplication
@Component(modules = [
    AndroidSupportInjectionModule::class,
    Bindings::class,
    AppModule::class,
    BaseModule::class,
    NetworkModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}
