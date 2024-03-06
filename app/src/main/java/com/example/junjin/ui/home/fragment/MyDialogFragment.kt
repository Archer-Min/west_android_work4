package com.example.junjin.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.junjin.R
import com.example.junjin.databinding.FragmentMyDialogBinding
import com.example.junjin.model.network.api.ArticleApi
import com.example.junjin.model.network.dto.SendCommentRequest
import kotlinx.coroutines.launch

class MyDialogFragment(private val index: Int) : DialogFragment() {

    private var _binding: FragmentMyDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyDialogBinding.inflate(inflater, container, false)
        //点击评论区外退出弹窗
        binding.root.setOnClickListener{
            dismiss()
        }
        binding.view.setOnClickListener{

        }
        binding.send.setOnClickListener{
            val comment = binding.commentInput.text
            val sendCommentRequest = SendCommentRequest(comment.toString())
            lifecycleScope.launch{
                val result = ArticleApi.sendComment(index.toString(),sendCommentRequest)
                if (result.code == 200){
                    Log.d("comment","评论成功！")
                }
                dismiss()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 在这里可以设置弹框的内容和行为
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置弹框样式为底部显示
        setStyle(STYLE_NORMAL, R.style.BottomDialog)
    }
}