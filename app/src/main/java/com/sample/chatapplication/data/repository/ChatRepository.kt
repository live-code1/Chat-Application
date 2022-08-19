package com.sample.chatapplication.data.repository

import com.sample.chatapplication.data.model.Message
import com.sample.chatapplication.data.model.User
import com.sample.chatapplication.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class ChatRepository @Inject constructor(private val backEndApi: ApiService) {

    suspend fun getUsers(): Response<MutableList<User>> {
        return backEndApi.getUsers()
    }

    suspend fun getMessages(sessionId: String): Response<MutableList<Message>> {
        return backEndApi.getMessages(sessionId)
    }


}