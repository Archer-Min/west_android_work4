package com.example.junjin.model.network.dto

/**
 * comment
 *
 * 子评论
 */
data class Comment (
    /**
     * 评论内容
     */
    val context: String,

    /**
     * 评论日期
     */
    val date: String,

    /**
     * 评论id
     */
    val id: String,

    /**
     * 子评论
     */
    val subComments: Comment,

    val userId: Long,

    val username: String
)