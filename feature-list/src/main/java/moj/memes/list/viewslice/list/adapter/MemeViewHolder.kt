package moj.memes.list.viewslice.list.adapter

import android.arch.lifecycle.MutableLiveData
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_meme.*
import moj.memes.base.view.adapter.BaseViewHolder
import moj.memes.base.model.Meme
import moj.memes.list.viewslice.list.ListViewSlice.Action

class MemeViewHolder(
        itemView: View,
        private val actionLiveData: MutableLiveData<Action>
) : BaseViewHolder<Meme>(itemView) {

    override fun bind(data: Meme) {
        setName(data.name)
        setImage(data.imageUrl, data.imageWidth, data.imageHeight)
        setViewClickListener(actionLiveData, data)
    }

    private fun setViewClickListener(actionLiveData: MutableLiveData<Action>, data: Meme) {
        itemView.setOnClickListener { actionLiveData.value = Action.MemeClicked(data) }
    }

    private fun setName(name: String) {
        meme_name.text = name
    }

    private fun setImage(imageUrl: String, imageWidth: Int, imageHeight: Int) {
        val layoutParams = meme_image.layoutParams
        layoutParams.width = imageWidth
        layoutParams.height = imageHeight
        meme_image.layoutParams = layoutParams
        Picasso.with(context).load(imageUrl).into(meme_image)
    }
}
