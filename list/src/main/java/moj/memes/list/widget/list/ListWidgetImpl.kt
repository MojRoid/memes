package moj.memes.list.widget.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import moj.memes.base.widget.BaseWidget
import moj.memes.list.model.Meme
import moj.memes.list.widget.list.adapter.MemesAdapter
import javax.inject.Inject

class ListWidgetImpl @Inject constructor(
        private val layoutManager: LinearLayoutManager,
        private val adapter: MemesAdapter
) : BaseWidget(),
        ListWidget {

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