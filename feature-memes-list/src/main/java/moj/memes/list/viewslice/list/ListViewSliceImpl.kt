package moj.memes.list.viewslice.list

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import moj.memes.base.viewslice.BaseViewSlice
import moj.memes.base.model.Meme
import moj.memes.list.viewslice.list.ListViewSlice.Action
import moj.memes.list.viewslice.list.adapter.MemesAdapter
import javax.inject.Inject

class ListViewSliceImpl @Inject constructor(
        private val actionLiveData: MutableLiveData<Action>,
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

    override fun getAction(): LiveData<Action> = actionLiveData

    override fun showMemes(memes: List<Meme>) {
        adapter.setMemes(memes)
    }
}
