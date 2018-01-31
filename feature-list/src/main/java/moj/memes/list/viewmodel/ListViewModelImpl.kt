package moj.memes.list.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import moj.memes.list.domain.FetchMemesUseCase

class ListViewModelImpl(
        private val state: MediatorLiveData<State>,
        private val fetchMemesUseCase: FetchMemesUseCase
) : ListViewModel() {

    init {
        state.addSource(fetchMemesUseCase.getLiveData(), ::onFetchMemesResult)
    }

    override fun onCleared() {
        fetchMemesUseCase.cleanUp()
    }

    override fun getState(): LiveData<State> = state

    override fun fetchMemes() {
        state.value = State.ShowLoading
        fetchMemesUseCase.execute()
    }

    private fun onFetchMemesResult(result: FetchMemesUseCase.Result?) {
        when (result) {
            is FetchMemesUseCase.Result.OnSuccess -> {
                state.value = State.MemesLoaded(result.memes)
                state.value = State.ShowContent
            }
            is FetchMemesUseCase.Result.OnError -> state.value = State.ShowError
        }
    }
}
