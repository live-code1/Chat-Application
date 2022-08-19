package com.sample.chatapplication.data.model

import com.google.gson.annotations.SerializedName

data class User  (

    @SerializedName("user_id" ) var userId : Int?    = null,
    @SerializedName("user_name"  ) var userName  : String? = null,
    @SerializedName("last_message"   ) var lastMessage   : String? = null,
    @SerializedName("last_message_time"   ) var messageTime   : String? = null,
    @SerializedName("image_url"   ) var imageUrl   : String? = null


)