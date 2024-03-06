package com.example.junjin.model.network.api

import com.example.junjin.model.network.RetrofitProvider
import com.example.junjin.model.network.service.AccountService

object AccountApi {
    private val accountService by lazy {
        RetrofitProvider.getRetrofit(true).create(AccountService::class.java)
    }

    suspend fun login(username: String, password: String) =
        accountService.login(username, password)

    suspend fun register(username: String, password: String) =
        accountService.register(username, password)

    suspend fun getAvatar(userId: Int) =
        accountService.getAvatar(userId)

    suspend fun getMyLikes() = accountService.getLikes()
}