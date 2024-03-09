package com.example.junjin.model.network.api

import com.example.junjin.model.network.RetrofitProvider
import com.example.junjin.model.network.service.AccountService
import okhttp3.MultipartBody

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

    suspend fun getMyEssays(userId: String) = accountService.getMyEssays(userId)

    suspend fun changeName(newUsername: String) = accountService.changeName(newUsername)

    suspend fun changePassword(newPassword: String) = accountService.changePassword(newPassword)

    suspend fun changeAvatar(filePart: MultipartBody.Part) = accountService.changeAvatar(filePart)

    suspend fun getUserInfo(userId: Int) = accountService.getUserInfo(userId)
}