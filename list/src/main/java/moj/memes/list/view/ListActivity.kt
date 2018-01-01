package moj.memes.list.view

import android.arch.lifecycle.Observer
import android.os.Bundle
import moj.memes.base.extension.initWidgets
import moj.memes.base.view.BaseActivity
import moj.memes.list.R
import moj.memes.list.widget.list.ListWidget
import moj.memes.list.widget.state.ListStateWidget
import moj.memes.list.viewmodel.ListViewModel
import moj.memes.list.viewmodel.ListViewModel.State
import javax.inject.Inject

class ListActivity(override val layoutResourceId: Int = R.layout.activity_list) : BaseActivity() {

    @Inject lateinit var viewModel: ListViewModel
    @Inject lateinit var listStateWidget: ListStateWidget
    @Inject lateinit var listWidget: ListWidget

    override public fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWidgets(listStateWidget, listWidget)
        setUpViewModel()
        viewModel.fetchMemes()
    }

    private fun setUpViewModel() {
        viewModel.getState().observe(this, Observer { it?.let { onStateChanged(it) } })
    }

    private fun onStateChanged(state: State) = when (state) {
        is State.MemesLoaded -> listWidget.showMemes(state.memes)
        is State.ShowLoading -> listStateWidget.showLoading()
        is State.ShowContent -> listStateWidget.showContent()
        is State.ShowError -> listStateWidget.showError()
    }
}