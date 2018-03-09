package moj.memes.list.injection

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import dagger.Module
import dagger.Provides
import moj.memes.base.injection.qualifiers.ForActivity
import moj.memes.base.injection.scopes.PerActivity
import moj.memes.list.domain.FetchMemesUseCase
import moj.memes.list.domain.FetchMemesUseCaseImpl
import moj.memes.list.repository.MemesRepository
import moj.memes.list.repository.MemesRepositoryImpl
import moj.memes.list.view.MemesListActivity
import moj.memes.list.viewslice.list.injection.ListViewSliceModule
import moj.memes.list.viewslice.state.injection.StateViewSliceModule
import moj.memes.list.viewmodel.ListViewModel
import moj.memes.list.viewmodel.ListViewModelFactory

@Module(includes = [
    MemesListModule.Repository::class,
    MemesListModule.UseCase::class,
    MemesListModule.ViewModel::class,
    MemesListModule.View::class,
    ListViewSliceModule::class,
    StateViewSliceModule::class
])
class MemesListModule {

    @Module
    class Repository {
        @Provides
        @PerActivity
        fun provideMemesRepository(repository: MemesRepositoryImpl): MemesRepository = repository
    }

    @Module
    class UseCase {
        @Provides
        @PerActivity
        fun provideFetchMemesUseCase(useCase: FetchMemesUseCaseImpl): FetchMemesUseCase = useCase
    }

    @Module
    class ViewModel {
        @Provides
        @PerActivity
        fun provideListViewModel(activity: MemesListActivity, factory: ListViewModelFactory): ListViewModel =
                ViewModelProviders.of(activity, factory).get(ListViewModel::class.java)
    }

    @Module
    class View {
        @Provides
        @PerActivity
        @ForActivity
        fun provideContext(activity: MemesListActivity): Context = activity
    }
}
