package moj.memes.app.injection

import dagger.Module
import dagger.Provides
import moj.memes.app.view.ScreenRouterImpl
import moj.memes.base.injection.scopes.PerApplication
import moj.memes.base.view.ScreenRouter

@Module
class AppModule {

    @Provides
    @PerApplication
    fun provideScreenRouter(): ScreenRouter = ScreenRouterImpl()
}
