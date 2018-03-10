package moj.memes.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_memes_detail.*
import moj.memes.base.model.EXTRA_MEME
import moj.memes.base.model.Meme

class MemesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memes_detail)
        getExtras()
    }

    private fun getExtras() {
        val meme: Meme? = intent.extras?.getParcelable(EXTRA_MEME)
        meme?.let { meme_text_view.text = it.toString() }
    }
}
