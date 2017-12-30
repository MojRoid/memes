package moj.memes.list.view.widget.list

import moj.memes.base.view.widget.Widget
import moj.memes.list.model.Meme

interface ListWidget : Widget {

    fun showMemes(memes: List<Meme>)
}