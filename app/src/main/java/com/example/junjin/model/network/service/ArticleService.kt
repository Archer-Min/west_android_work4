package com.example.junjin.model.network.service

import com.example.junjin.base.dto.BaseDto
import com.example.junjin.model.network.dto.Article
import com.example.junjin.model.network.dto.SendCommentRequest
import com.example.junjin.model.network.dto.PublishRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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
    //发布评论
    @POST("/article/{articleId}/comment")
    suspend fun sendComment(
        @Path("articleId") articleId: String,
        @Body body: SendCommentRequest
    ): BaseDto<Any>
    //对评论进行评论
    @POST("/article/{articleId}/{commentId}/comment")
    suspend fun sendSubComment(
        @Path("articleId") articleId: String,
        @Path("commentId") commentId: String,
        @Body body: SendCommentRequest
    ): BaseDto<Any>
    //发表文章
    @POST("/article/write-article")
    suspend fun publish(
        @Body body: PublishRequest
    ): BaseDto<Any>
}