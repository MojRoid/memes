package moj.memes.list.viewslice.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import moj.memes.base.viewslice.BaseViewSlice
import moj.memes.list.model.Meme
import moj.memes.list.viewslice.list.adapter.MemesAdapter
import javax.inject.Inject

class ListViewSliceImpl @Inject constructor(
        private val layoutManager: LinearLayoutManager,
        private val adapter: MemesAdapter
) : BaseViewSlice(),
        ListViewSlice {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        memes_recycler_view.layoutManager = layoutManager
        memes_recycler_view.adapter = adapter
    }

    override fun showMemes(memes: List<Meme>) {
        adapter.setMemes(memes)
    }
}