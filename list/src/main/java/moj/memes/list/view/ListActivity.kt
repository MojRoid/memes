package moj.memes.list.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import moj.memes.base.view.BaseActivity
import moj.memes.list.R
import moj.memes.list.view.adapter.MemesAdapter
import moj.memes.list.viewmodel.ListViewModel
import moj.memes.list.viewmodel.ListViewModel.State
import javax.inject.Inject

private const val STATE_CONTENT = 0
private const val STATE_LOADING = 1
private const val STATE_ERROR = 2

class ListActivity(override val layoutResourceId: Int = R.layout.activity_list) : BaseActivity() {

    @Inject lateinit var viewModel: ListViewModel
    @Inject lateinit var layoutManager: LinearLayoutManager
    @Inject lateinit var adapter: MemesAdapter

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpRecyclerView()
        setUpViewModel()
        viewModel.fetchMemes()
    }

    private fun setUpRecyclerView() {
        memes_recycler_view.layoutManager = layoutManager
        memes_recycler_view.adapter = adapter
    }

    private fun setUpViewModel() {
        viewModel.getState().observe(this, Observer { it?.let { onStateChanged(it) } })
    }

    private fun onStateChanged(state: State) = when (state) {
        is State.MemesLoaded -> adapter.setMemes(state.memes)
        is State.ShowLoading -> showState(STATE_LOADING)
        is State.ShowContent -> showState(STATE_CONTENT)
        is State.ShowError -> showState(STATE_ERROR)
    }

    private fun showState(state: Int) {
        if (list_state_view_flipper.displayedChild != state) {
            list_state_view_flipper.displayedChild = state
        }
    }
}