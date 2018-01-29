package moj.memes.list.viewslice.list

import moj.memes.base.viewslice.ViewSlice
import moj.memes.list.model.Meme

interface ListViewSlice : ViewSlice {

    fun showMemes(memes: List<Meme>)
}