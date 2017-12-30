package moj.memes.list.view.widget.state

import kotlinx.android.synthetic.main.activity_list.*
import moj.memes.base.view.widget.BaseWidget
import javax.inject.Inject

private const val STATE_CONTENT = 0
private const val STATE_LOADING = 1
private const val STATE_ERROR = 2

class ListStateWidgetImpl @Inject constructor(
) : BaseWidget(),
        ListStateWidget {

    override fun showLoading() {
        showState(STATE_LOADING)
    }

    override fun showContent() {
        showState(STATE_CONTENT)
    }

    override fun showError() {
        showState(STATE_ERROR)
    }

    private fun showState(state: Int) {
        if (list_state_view_flipper.displayedChild != state) {
            list_state_view_flipper.displayedChild = state
        }
    }
}