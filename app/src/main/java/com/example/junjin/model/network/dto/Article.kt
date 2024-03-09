package com.example.junjin.model.network.dto

data class Article (
    val articleId: Int,
    var clickCount: Int,
    val comments: List<Comment>,
    val date: String,
    var like: Int,
    val title: String,
    val userId: Int,
    val username: String,
    val context: String
)