package com.example.junjin.model.network.dto

data class SubComment(
    val subComments: List<SubComment>? = null,
    val date: String,
    val userId: Int,
    val articleId: Int,
    val fatherCommentId: String,
    val isSubComment: Int,
    val context: String,
    val username: String,
    val avatar: String,
    val id: String,
)
