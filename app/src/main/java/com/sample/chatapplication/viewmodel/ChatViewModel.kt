package com.sample.chatapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sample.chatapplication.data.model.Message
import com.sample.chatapplication.data.model.User
import com.sample.chatapplication.data.network.ResultResponse
import com.sample.chatapplication.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ChatViewModel
@Inject constructor(
    private var repository: ChatRepository
) : ViewModel() {

    fun getUsers(
    ): LiveData<ResultResponse<Response<MutableList<User>>>> = liveData {
        var responseGet: Response<MutableList<User>>? = null
        kotlin.runCatching {
            responseGet = repository.getUsers(
            )
        }.onSuccess {
            when (responseGet?.code()) {
                200 -> {
                    emit(ResultResponse.success(responseGet!!))
                }
                502, 522, 523, 500 -> {
                    emit(ResultResponse.error(responseGet!!.message().toString(), responseGet))
                }
                else -> {
                    emit(ResultResponse.error(responseGet!!.message().toString(), responseGet))
                }
            }
        }.onFailure {
            emit(ResultResponse.error(it.message.toString(), responseGet))
        }
    }

    fun getMessages(sessionId: String
    ): LiveData<ResultResponse<Response<MutableList<Message>>>> = liveData {
        var responseGet: Response<MutableList<Message>>? = null
        kotlin.runCatching {
            responseGet = repository.getMessages(sessionId
            )
        }.onSuccess {
            when (responseGet?.code()) {
                200 -> {
                    emit(ResultResponse.success(responseGet!!))
                }
                502, 522, 523, 500 -> {
                    emit(ResultResponse.error(responseGet!!.message().toString(), responseGet))
                }
                else -> {
                    emit(ResultResponse.error(responseGet!!.message().toString(), responseGet))
                }
            }
        }.onFailure {
            emit(ResultResponse.error(it.message.toString(), responseGet))
        }
    }

}