package moj.memes.list.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import moj.memes.base.extension.initViewSlices
import moj.memes.base.view.BaseActivity
import moj.memes.list.R
import moj.memes.list.viewslice.list.ListViewSlice
import moj.memes.list.viewslice.state.ListStateViewSlice
import moj.memes.list.viewmodel.ListViewModel
import moj.memes.list.viewmodel.ListViewModel.State
import javax.inject.Inject

class ListActivity(override val layoutResourceId: Int = R.layout.activity_list) : BaseActivity() {

    @Inject lateinit var viewModel: ListViewModel
    @Inject lateinit var listStateViewSlice: ListStateViewSlice
    @Inject lateinit var listViewSlice: ListViewSlice

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewSlices(listStateViewSlice, listViewSlice)
        setUpViewModel()
        viewModel.fetchMemes()
    }

    private fun setUpViewModel() {
        viewModel.getState().observe(this, Observer { it?.let { onStateChanged(it) } })
    }

    private fun onStateChanged(state: State) = when (state) {
        is State.MemesLoaded -> listViewSlice.showMemes(state.memes)
        State.ShowLoading -> listStateViewSlice.showLoading()
        State.ShowContent -> listStateViewSlice.showContent()
        State.ShowError -> listStateViewSlice.showError()
    }
}