package com.sample.chatapplication.data.model

import com.google.gson.annotations.SerializedName

data class Message  (

    @SerializedName("message_id" ) var messageId : Int?    = null,
    @SerializedName("message_type"   ) var messageType   : Int? = null,
    @SerializedName("user_image"  ) var avatar  : String? = null,
    @SerializedName("body"   ) var chatBody   : String? = null,
    @SerializedName("send_time"   ) var sendTime   : String? = null



)