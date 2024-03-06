package com.example.junjin.model.network.dto

data class MyLikeData(
    val userId: Int,
    val username: String,
    val articleId: Int,
    val title: String,
    val clickCount: Int,
    val date: String,
    val comments: Any?,
    val like: Int,
    val context: Any?
)
