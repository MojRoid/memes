package moj.memes.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_demo.*
import moj.memes.base.model.EXTRA_MEME
import moj.memes.base.model.Meme

class MemesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        getExtras()
    }

    private fun getExtras() {
        val meme: Meme? = intent.extras?.getParcelable(EXTRA_MEME)
        meme?.let { demo_text.text = it.toString() }
    }
}
