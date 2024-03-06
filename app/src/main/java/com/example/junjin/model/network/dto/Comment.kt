package com.example.junjin.model.network.dto

/**
 * comment
 *
 * 子评论
 */
data class Comment (
    val context: String,

    val date: String,

    val id: String,

    val subComments: List<SubComment>,

    val userId: Long,

    val username: String,

    val avatar: String,
)