package com.example.junjin.ui.my.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.model.network.api.ArticleApi
import com.example.junjin.model.network.dto.Article

class MyEssayViewModel: ViewModel() {
    private val _essayList = MutableLiveData<List<Article>>()
    val essayList: LiveData<List<Article>> = _essayList

    suspend fun getMyEssays(userId: Int) {
        try {
            val result = AccountApi.getMyEssays(userId.toString())
            if (result.code == 200) {
                _essayList.value = result.data // 更新 LiveData 中的数据
                Log.d("MyEssayViewModel", "成功获取我的文章列表")
            } else {
                // 处理请求失败的情况
                Log.d("EssayViewModel", "Failed to fetch essay list. Error code: ${result.code}")
            }
        } catch (e: Exception) {
            // 处理异常情况
            Log.e("EssayViewModel", "Exception occurred while fetching essay list", e)
        }
    }

    suspend fun getMyLikes(){
        try {
            val result = AccountApi.getMyLikes()
            if (result.code == 200) {
                _essayList.value = result.data // 更新 LiveData 中的数据
                Log.d("MyEssayViewModel", "成功获取我的文章列表")
            } else {
                // 处理请求失败的情况
                Log.d("EssayViewModel", "Failed to fetch essay list. Error code: ${result.code}")
            }
        } catch (e: Exception) {
            // 处理异常情况
            Log.e("EssayViewModel", "Exception occurred while fetching essay list", e)
        }
    }
}