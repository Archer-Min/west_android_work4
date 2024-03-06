package com.example.junjin.model.network.service

import com.example.junjin.base.dto.BaseDto
import com.example.junjin.model.network.dto.MyLikeData
import com.example.junjin.model.network.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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
    suspend fun getLikes():BaseDto<List<MyLikeData>>
}