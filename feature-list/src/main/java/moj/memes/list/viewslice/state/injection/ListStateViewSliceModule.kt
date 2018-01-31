package moj.memes.list.viewslice.state.injection

import dagger.Module
import dagger.Provides
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.viewslice.state.ListStateViewSlice
import moj.memes.list.viewslice.state.ListStateViewSliceImpl

@Module
class ListStateViewSliceModule {

    @Provides
    @PerActivity
    fun provideListStateViewSlice(viewSlice: ListStateViewSliceImpl): ListStateViewSlice = viewSlice
}
