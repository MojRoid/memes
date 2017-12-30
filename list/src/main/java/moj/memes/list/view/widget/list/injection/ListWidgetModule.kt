package moj.memes.list.view.widget.list.injection

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import moj.memes.base.injection.qualifiers.ForActivity
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.view.widget.list.ListWidget
import moj.memes.list.view.widget.list.ListWidgetImpl
import moj.memes.list.view.widget.list.adapter.MemesAdapter
import moj.memes.list.view.widget.list.adapter.MemesAdapterImpl

@Module
class ListWidgetModule {

    @Provides
    @PerActivity
    fun provideListWidget(widget: ListWidgetImpl): ListWidget = widget

    @Provides
    @PerActivity
    fun provideLayoutManager(@ForActivity context: Context): LinearLayoutManager =
            LinearLayoutManager(context)

    @Provides
    @PerActivity
    fun provideMemesAdapter(adapter: MemesAdapterImpl): MemesAdapter = adapter
}