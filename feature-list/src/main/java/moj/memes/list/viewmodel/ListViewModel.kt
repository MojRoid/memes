package moj.memes.list.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import moj.memes.base.model.Meme

abstract class ListViewModel : ViewModel() {

    sealed class State {
        data class MemesLoaded(val memes: List<Meme>) : State()
        object ShowLoading : State()
        object ShowContent : State()
        object ShowError : State()
    }

    abstract fun getState(): LiveData<State>

    abstract fun fetchMemes()
}
