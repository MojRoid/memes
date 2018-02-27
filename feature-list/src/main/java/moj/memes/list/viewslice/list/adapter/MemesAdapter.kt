package moj.memes.list.viewslice.list.adapter

import android.support.v7.widget.RecyclerView
import moj.memes.base.model.Meme

abstract class MemesAdapter : RecyclerView.Adapter<MemeViewHolder>() {

    abstract fun setMemes(memes: List<Meme>)

    abstract fun addMemes(memes: List<Meme>)

    abstract fun clearMemes()
}
