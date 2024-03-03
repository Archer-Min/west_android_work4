package com.example.junjin.model.network.api

import com.example.junjin.model.network.RetrofitProvider
import com.example.junjin.model.network.service.ArticleService

object ArticleApi {
    private val articleService by lazy {
        RetrofitProvider.getRetrofit(true).create(ArticleService::class.java)
    }

    suspend fun getEssayClickList(numArticles: Int) = articleService.getEssayClickList(numArticles)

    suspend fun getEssayDateList(offset: Int) = articleService.getEssayLatestList(offset)

    suspend fun getEssayDetail(articleId: String) = articleService.getEssayDetail(articleId)
}