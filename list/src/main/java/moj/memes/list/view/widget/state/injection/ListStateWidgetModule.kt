package moj.memes.list.view.widget.state.injection

import dagger.Module
import dagger.Provides
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.view.widget.state.ListStateWidget
import moj.memes.list.view.widget.state.ListStateWidgetImpl

@Module
class ListStateWidgetModule {

    @Provides
    @PerActivity
    fun provideListStateWidget(widget: ListStateWidgetImpl): ListStateWidget = widget
}