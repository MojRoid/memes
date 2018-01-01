package moj.memes.list.widget.list

import moj.memes.base.widget.Widget
import moj.memes.list.model.Meme

interface ListWidget : Widget {

    fun showMemes(memes: List<Meme>)
}