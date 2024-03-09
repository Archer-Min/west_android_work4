package com.example.junjin.ui.write

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.junjin.R
import com.example.junjin.base.ui.BaseActivity
import com.example.junjin.databinding.ActivityPreviewBinding
import io.noties.markwon.Markwon

class PreviewActivity : BaseActivity<ActivityPreviewBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val markdownText = intent.getStringExtra(EXTRA_MARKDOWN_TEXT)
        if (!markdownText.isNullOrEmpty()) {
            val markwon = Markwon.create(this)
            val markdown = markwon.toMarkdown(markdownText)
            binding.markdownView.text = markdown
        }
    }
    companion object {
        const val EXTRA_MARKDOWN_TEXT = "extra_markdown_text"

        fun start(context: Context, markdownText: String) {
            val intent = Intent(context, PreviewActivity::class.java).apply {
                putExtra(EXTRA_MARKDOWN_TEXT, markdownText)
            }
            context.startActivity(intent)
        }
    }
}