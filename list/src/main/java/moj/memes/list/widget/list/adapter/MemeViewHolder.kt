package moj.memes.list.widget.list.adapter

import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_holder_meme.*
import moj.memes.base.view.adapter.BaseViewHolder
import moj.memes.list.model.Meme

class MemeViewHolder(itemView: View) : BaseViewHolder<Meme>(itemView) {

    override fun bind(data: Meme) {
        setName(data.name)
        setImage(data.imageUrl, data.imageWidth, data.imageHeight)
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