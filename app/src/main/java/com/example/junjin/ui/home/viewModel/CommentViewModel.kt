package com.example.junjin.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.junjin.model.network.api.ArticleApi
import com.example.junjin.model.network.dto.Comment

class CommentViewModel :ViewModel(){
    private val _commentList = MutableLiveData<List<Comment>>()
    val commentList: LiveData<List<Comment>> = _commentList

    suspend fun getCommentList(articleId: String){
        try {
            val result = ArticleApi.getEssayDetail(articleId)
            if (result.code == 200){
                _commentList.value = result.data?.comments
                Log.d("getComment", result.data.toString())
            }
        }catch (e: Exception){

        }
    }

    fun getCommentNumber(): Int? {
        val currentList = _commentList.value ?: return 0
        return currentList.size
    }
}