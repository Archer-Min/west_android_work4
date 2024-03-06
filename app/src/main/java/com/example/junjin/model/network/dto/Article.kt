package com.example.junjin.model.network.dto

data class Article (
    /**
     * 文章id，所有的文章都有唯一id
     */
    val articleId: Int,

    /**
     * 点击次数
     */
    var clickCount: Int,

    /**
     * 评论
     */
    val comments: List<Comment>,

    /**
     * 发布日期
     */
    val date: String,

    /**
     * 点赞数
     */
    var like: Int,

    /**
     * 文章标题
     */
    val title: String,

    val userId: Int,

    val username: String
)