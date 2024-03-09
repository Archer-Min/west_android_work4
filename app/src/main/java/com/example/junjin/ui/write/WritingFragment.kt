package com.example.junjin.ui.write

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.junjin.R
import com.example.junjin.base.ui.BaseFragment
import com.example.junjin.databinding.FragmentWritingBinding
import com.example.junjin.model.network.api.ArticleApi
import com.example.junjin.model.network.dto.PublishRequest
import kotlinx.coroutines.launch

class WritingFragment : BaseFragment<FragmentWritingBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWritingBinding.inflate(inflater, container, false)
        val view  = binding.root
        val title = binding.markdownTitle.text.toString()
        val markdownText = binding.markdownContentText.text.toString()

        val publishRequest = PublishRequest(title,markdownText)
        binding.publish.setOnClickListener{
            lifecycleScope.launch{
                val result = ArticleApi.publishEssay(publishRequest)
                if (result.code == 200){
                    Log.d("publish","发表文章成功！")
                }
            }
        }
        binding.view.setOnClickListener{
            val markdownText = "# "+binding.markdownTitle.text.toString()+"\n"+binding.markdownContentText.text.toString()
            PreviewActivity.start(requireContext(), markdownText)
        }
        return view
    }
}