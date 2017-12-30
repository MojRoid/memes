package moj.memes.app.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.injection.ListModule
import moj.memes.list.view.ListActivity

@Module
abstract class Bindings {

    @PerActivity
    @ContributesAndroidInjector(modules = [ListModule::class])
    abstract fun bindListActivity(): ListActivity
}