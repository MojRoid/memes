package moj.memes.list.viewslice.list.injection

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import moj.memes.base.injection.qualifiers.ForActivity
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.viewslice.list.ListViewSlice
import moj.memes.list.viewslice.list.ListViewSlice.Action
import moj.memes.list.viewslice.list.ListViewSliceImpl
import moj.memes.list.viewslice.list.adapter.MemesAdapter
import moj.memes.list.viewslice.list.adapter.MemesAdapterImpl

@Module
class ListViewSliceModule {

    @Provides
    @PerActivity
    fun provideListViewSlice(viewSlice: ListViewSliceImpl): ListViewSlice = viewSlice

    @Provides
    @PerActivity
    fun provideListActionLiveData(): MutableLiveData<Action> = MutableLiveData()

    @Provides
    @PerActivity
    fun provideLayoutManager(@ForActivity context: Context): LinearLayoutManager =
            LinearLayoutManager(context)

    @Provides
    @PerActivity
    fun provideMemesAdapter(adapter: MemesAdapterImpl): MemesAdapter = adapter
}