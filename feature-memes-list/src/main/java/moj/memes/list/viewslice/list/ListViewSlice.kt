package moj.memes.list.viewslice.list

import android.arch.lifecycle.LiveData
import moj.memes.base.viewslice.ViewSlice
import moj.memes.base.model.Meme

interface ListViewSlice : ViewSlice {

    sealed class Action {
        data class MemeClicked(val meme: Meme) : Action()
    }

    fun getAction(): LiveData<Action>

    fun showMemes(memes: List<Meme>)
}
