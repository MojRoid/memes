package moj.memes.list.viewslice.state.injection

import dagger.Module
import dagger.Provides
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.viewslice.state.StateViewSlice
import moj.memes.list.viewslice.state.StateViewSliceImpl

@Module
class StateViewSliceModule {

    @Provides
    @PerActivity
    fun provideStateViewSlice(viewSlice: StateViewSliceImpl): StateViewSlice = viewSlice
}
