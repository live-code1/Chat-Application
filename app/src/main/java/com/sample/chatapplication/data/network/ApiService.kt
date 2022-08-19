package com.sample.chatapplication.data.network

import com.sample.chatapplication.data.model.Message
import com.sample.chatapplication.data.model.User
import retrofit2.Response
import retrofit2.http.*


interface ApiService {


    @GET("getUsers")
    suspend fun getUsers(
    ): Response<MutableList<User>>

    @GET("getMessages")
    suspend fun getMessages(
        @Query("session_id") sessionId: String
    ): Response<MutableList<Message>>





}


