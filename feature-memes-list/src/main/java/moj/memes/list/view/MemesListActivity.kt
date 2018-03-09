package moj.memes.list.view

import android.content.Intent
import android.os.Bundle
import moj.memes.base.extension.getContentView
import moj.memes.base.extension.observe
import moj.memes.base.model.EXTRA_MEME
import moj.memes.base.model.Meme
import moj.memes.base.view.BaseActivity
import moj.memes.base.view.ScreenRouter
import moj.memes.base.view.ScreenRouter.Screen.Detail
import moj.memes.list.R
import moj.memes.list.viewmodel.ListViewModel
import moj.memes.list.viewmodel.ListViewModel.State
import moj.memes.list.viewslice.list.ListViewSlice
import moj.memes.list.viewslice.state.StateViewSlice
import javax.inject.Inject

class MemesListActivity(override val layoutResourceId: Int = R.layout.activity_list) : BaseActivity() {

    @Inject lateinit var screenRouter: ScreenRouter
    @Inject lateinit var viewModel: ListViewModel
    @Inject lateinit var stateViewSlice: StateViewSlice
    @Inject lateinit var listViewSlice: ListViewSlice

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewSlices()
        setUpViewSliceActionObservers()
        setUpViewModelStateObservers()
        viewModel.fetchMemes()
    }

    private fun initViewSlices() {
        stateViewSlice.init(lifecycle, getContentView())
        listViewSlice.init(lifecycle, getContentView())
    }

    private fun setUpViewSliceActionObservers() {
        observe(listViewSlice.getAction()) { onActionChanged(it) }
    }

    private fun onActionChanged(action: ListViewSlice.Action) = when (action) {
        is ListViewSlice.Action.MemeClicked -> startMemesDetailActivity(action.meme)
    }

    private fun startMemesDetailActivity(meme: Meme) {
        val intent: Intent? = screenRouter.getScreenIntent(this, Detail)
        intent?.apply {
            putExtra(EXTRA_MEME, meme)
        }?.run { startActivity(this) }
    }

    private fun setUpViewModelStateObservers() {
        observe(viewModel.getState()) { onStateChanged(it) }
    }

    private fun onStateChanged(state: State) = when (state) {
        is State.MemesLoaded -> listViewSlice.showMemes(state.memes)
        State.ShowLoading -> stateViewSlice.showLoading()
        State.ShowContent -> stateViewSlice.showContent()
        State.ShowError -> stateViewSlice.showError()
    }
}
