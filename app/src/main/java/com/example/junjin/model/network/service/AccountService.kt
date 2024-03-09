package com.example.junjin.model.network.service

import com.example.junjin.base.dto.BaseDto
import com.example.junjin.model.network.dto.Article
import com.example.junjin.model.network.dto.UserDto
import com.example.junjin.model.network.dto.UserInfo
import okhttp3.MultipartBody
import retrofit2.http.*

interface AccountService {
    @POST("user/login/")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): BaseDto<UserDto>

    @POST("user/register/")
    suspend fun register(
        @Query("username") username: String,
        @Query("password") password: String
    ): BaseDto<Any>

    @GET("/getAvatar/{userId}")
    suspend fun getAvatar(
        @Path("userId") userId:Int
    ):BaseDto<String>

    @GET("/user/like")
    suspend fun getLikes():BaseDto<List<Article>>

    @GET("/article/user/{userId}")
    suspend fun getMyEssays(
        @Path("userId") userId: String
    ):BaseDto<List<Article>>

    @POST("/user/change-username")
    suspend fun changeName(
        @Query("newUsername") newUsername: String
    ):BaseDto<String>

    @POST("/user/change-password")
    suspend fun changePassword(
        @Query("newPassword") newPassword: String
    ):BaseDto<String>

    @POST("/user/change-avatar")
    suspend fun changeAvatar(
        @Part filePart: MultipartBody.Part
    ):BaseDto<String>

    @GET("/user/getUser/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Int
    ):BaseDto<UserInfo>
}