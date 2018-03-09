package moj.memes.list.viewmodel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import moj.memes.list.domain.FetchMemesUseCase
import javax.inject.Inject

class ListViewModelFactory @Inject constructor(
        private val fetchMemesUseCase: FetchMemesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(p0: Class<T>): T {
        return ListViewModelImpl(
                MediatorLiveData(),
                fetchMemesUseCase
        ) as T
    }
}
