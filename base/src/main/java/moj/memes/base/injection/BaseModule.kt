package moj.memes.base.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import moj.memes.base.injection.qualifiers.ForApplication
import moj.memes.base.injection.scopes.PerApplication
import moj.memes.base.view.adapter.DiffCallback
import timber.log.Timber

@Module
class BaseModule {

    @Provides
    @PerApplication
    @ForApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @PerApplication
    fun provideLogger(): Timber.Tree = Timber.DebugTree()

    @Provides
    @PerApplication
    fun provideDiffCallback(): DiffCallback = DiffCallback()
}
