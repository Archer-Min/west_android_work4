package com.example.junjin.ui.home.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.junjin.model.network.api.ArticleApi
import com.example.junjin.model.network.dto.Article

class EssayViewModel : ViewModel() {

    // 定义 LiveData 对象用于保存文章列表数据
    private val _essayList = MutableLiveData<List<Article>>()
    val essayList: LiveData<List<Article>> = _essayList

    // 获取文章列表(点击量排序）
    suspend fun getEssayClickList(numArticles: Int) {
        try {
            val result = ArticleApi.getEssayClickList(numArticles)
            if (result.code == 200) {
                _essayList.value = result.data // 更新 LiveData 中的数据
                Log.d("EssayViewModel", "成功获取点击量排序文章列表")
            } else {
                // 处理请求失败的情况
                Log.d("EssayViewModel", "Failed to fetch essay list. Error code: ${result.code}")
            }
        } catch (e: Exception) {
            // 处理异常情况
            Log.e("EssayViewModel", "Exception occurred while fetching essay list", e)
        }
    }
    //日期排序
    suspend fun getEssayDateList(offset: Int) {
        try {
            val result = ArticleApi.getEssayDateList(offset)
            if (result.code == 200) {
                _essayList.value = result.data // 更新 LiveData 中的数据
                Log.d("EssayViewModel", "成功获取最新文章列表")
            } else {
                // 处理请求失败的情况
                Log.d("EssayViewModel", "Failed to fetch essay list. Error code: ${result.code}")
            }
        } catch (e: Exception) {
            // 处理异常情况
            Log.e("EssayViewModel", "Exception occurred while fetching essay list", e)
        }
    }
    fun incrementClickCount(position: Int){
        val currentList = _essayList.value ?: return
        if (position >= 0 && position < currentList.size) {
            val updatedList = currentList.toMutableList()
            updatedList[position].clickCount++
            _essayList.value = updatedList
        }
    }

    fun incrementLike(position: Int){
        val currentList = _essayList.value ?: return
        if (position >= 0 && position < currentList.size) {
            val updatedList = currentList.toMutableList()
            updatedList[position].like++
            _essayList.value = updatedList
        }
    }

    fun cancelLike(position: Int){
        val currentList = _essayList.value ?: return
        if (position >= 0 && position < currentList.size) {
            val updatedList = currentList.toMutableList()
            updatedList[position].like++
            _essayList.value = updatedList
        }
    }

    fun getTitle(position: Int): String? {
        val currentList = _essayList.value ?: return "无语了标题去哪了"
        if (position >= 0 && position < currentList.size) {
            return currentList[position].title
        }
        return "null"
    }

    fun getUserId(position: Int): Int? {
        val currentList = _essayList.value ?: return null
        if (position >= 0 && position < currentList.size) {
            return currentList[position].userId
        }
        return null
    }

    fun getUsername(position: Int): String? {
        val currentList = _essayList.value ?: return null
        if (position >= 0 && position < currentList.size) {
            return currentList[position].username
        }
        return "null"
    }

    fun getDate(position: Int): String? {
        val currentList = _essayList.value ?: return null
        if (position >= 0 && position < currentList.size) {
            return currentList[position].date
        }
        return "null"
    }

    fun getClicks(position: Int): Int? {
        val currentList = _essayList.value ?: return null
        if (position >= 0 && position < currentList.size) {
            return currentList[position].clickCount
        }
        return null
    }

    fun getLikes(position: Int): Int? {
        val currentList = _essayList.value ?: return null
        if (position >= 0 && position < currentList.size) {
            return currentList[position].like
        }
        return null
    }
}
