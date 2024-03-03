package com.example.junjin.model.network.service

import com.example.junjin.base.dto.BaseDto
import com.example.junjin.model.network.dto.Article
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticleService {
    //获取文章列表(点击量排序）
    @GET("/home/articles-ranking")
    suspend fun getEssayClickList(
        @Query("numArticles") numArticles:Int
    ): BaseDto<List<Article>>
    //获取文章列表(日期排序）
    @GET("/home/latest-articles")
    suspend fun getEssayLatestList(
        @Query("offset") offset:Int
    ): BaseDto<List<Article>>
    //获取文章信息
    @GET("/article/get/{articleId}")
    suspend fun getEssayDetail(
        @Path("articleId") articleId: String
    ): BaseDto<Article>
}