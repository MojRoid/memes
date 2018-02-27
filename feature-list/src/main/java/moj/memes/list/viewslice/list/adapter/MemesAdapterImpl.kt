package moj.memes.list.viewslice.list.adapter

import android.arch.lifecycle.MutableLiveData
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import moj.memes.base.view.adapter.DiffCallback
import moj.memes.list.R
import moj.memes.base.model.Meme
import moj.memes.list.viewslice.list.ListViewSlice
import javax.inject.Inject

class MemesAdapterImpl @Inject constructor(
        private val diffCallback: DiffCallback,
        private val actionLiveData: MutableLiveData<ListViewSlice.Action>
        ) : MemesAdapter() {

    private var memes: MutableList<Meme> = mutableListOf()

    override fun getItemViewType(position: Int) = R.layout.view_holder_meme

    override fun getItemCount() = memes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_meme, parent, false)
        return MemeViewHolder(itemView, actionLiveData)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        holder.bind(memes[position])
    }

    override fun setMemes(memes: List<Meme>) {
        calculateDiff(ArrayList(memes))
    }

    override fun addMemes(memes: List<Meme>) {
        val list = ArrayList(this.memes)
        list.addAll(memes)
        calculateDiff(list)
    }

    override fun clearMemes() {
        calculateDiff(emptyList())
    }

    private fun calculateDiff(memes: List<Meme>) {
        diffCallback.setLists(this.memes, memes)
        val result = DiffUtil.calculateDiff(diffCallback)
        this.memes.clear()
        this.memes.addAll(memes)
        result.dispatchUpdatesTo(this)
    }
}
