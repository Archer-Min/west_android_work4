package com.example.junjin.ui.home.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junjin.R
import com.example.junjin.base.ui.BaseActivity
import com.example.junjin.databinding.ActivityEssayBinding
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.model.network.api.ArticleApi
import com.example.junjin.ui.home.adapter.CommentAdapter
import com.example.junjin.ui.home.adapter.EssayAdapter
import com.example.junjin.ui.home.adapter.SubCommentAdapter
import com.example.junjin.ui.home.fragment.MyDialogFragment
import com.example.junjin.ui.home.fragment.SubDialogFragment
import com.example.junjin.ui.home.viewModel.CommentViewModel
import com.example.junjin.ui.home.viewModel.EssayViewModel
import io.noties.markwon.Markwon
import kotlinx.coroutines.launch

class EssayActivity : BaseActivity<ActivityEssayBinding>() {
    private lateinit var essayViewModel: EssayViewModel
    private lateinit var commentViewModel: CommentViewModel
    private lateinit var essayAdapter: EssayAdapter
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        essayViewModel = ViewModelProvider(this).get(EssayViewModel::class.java)
        essayAdapter = EssayAdapter(essayViewModel)

        val markwon = Markwon.create(this)
        val markdownText = getString(R.string.mark_down_text)

        val markdown = markwon.toMarkdown(markdownText)
        markwon.setParsedMarkdown(binding.markdown, markdown)
        var star = false
        val index = intent.getIntExtra("articleId",0)
        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)

        val fragmentManager = supportFragmentManager
        commentAdapter = CommentAdapter(fragmentManager,index.toString())

        lifecycleScope.launch {
            try {
//                val essayDetailsResult = ArticleApi.getEssayDetail(index.toString())
//                binding.theTitle.text = essayDetailsResult.data?.title

                essayViewModel.getEssayDateList(5)
                //获取作者头像
                essayViewModel.getUserId(index)?.let {
                    val base64String = AccountApi.getAvatar(index).data
                    val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
                    val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    binding.avatar.setImageBitmap(bitmap)
                }

                commentViewModel.getCommentList(index.toString())

                binding.theTitle.text = essayViewModel.getTitle(index)
                binding.username.text = essayViewModel.getUsername(index)
                binding.theDate.text = essayViewModel.getDate(index)
                binding.clickTimes.text = essayViewModel.getClicks(index).toString()
                binding.likesNumber.text = essayViewModel.getLikes(index).toString()
                binding.commentTitle.text = "评论 "+commentViewModel.getCommentNumber()

                binding.commentListRecycler.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = commentAdapter
                }



            } catch (e: Exception) {
                Log.e("Error", "Failed to fetch essay list: $e")
            }
        }

        binding.like.setOnClickListener{
            if(star){
                essayViewModel.cancelLike(index)
            }else{
                essayViewModel.incrementLike(index)
            }
        }

        val dialog = MyDialogFragment(index)
        binding.commitInput.setOnClickListener{
            dialog.show(supportFragmentManager,"MyDialogFragment")
        }

        essayViewModel.essayList.observe(this) { essays ->
            essayAdapter.submitList(essays)
        }
        commentViewModel.commentList.observe(this){ comments ->
            commentAdapter.submitList(comments)
        }
    }

}