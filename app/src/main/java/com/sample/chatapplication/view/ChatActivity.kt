package com.sample.chatapplication.view

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.sample.chatapplication.BaseActivity
import com.sample.chatapplication.R
import com.sample.chatapplication.data.network.ResultResponse
import com.sample.chatapplication.util.AppUtils
import com.sample.chatapplication.util.Utils
import com.sample.chatapplication.util.toast
import com.sample.chatapplication.view.adapter.ChatsRcvAdapter
import com.sample.chatapplication.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_chat.*

@AndroidEntryPoint
class ChatActivity : BaseActivity() {
    private val chatViewModel: ChatViewModel by viewModels()
    var userId = "1"
    private var userName = "user"
    private var userImage = "user"
    var mAdapter: ChatsRcvAdapter? = null
    private var mProgressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        userId=intent.getStringExtra("userId")?:"1"
        userName=intent.getStringExtra("userName")?:"user"
        userImage=intent.getStringExtra("userImage")?:"user"

        setImage(img_user_profile, userImage)
        txt_app_bar_title.text=userName
        showProgressDialog()
        rcv_messages.layoutManager = LinearLayoutManager(this)
        mAdapter = ChatsRcvAdapter(this)
        rcv_messages.adapter = mAdapter

        if (AppUtils.isNetworkAvailable(this)) {
            getMessages(userId)
        } else {
            toast(getString(R.string.no_network))
        }


        back_button.setOnClickListener {
            finish()
        }
    }

    //get messages of user from server
    private fun getMessages(sessionId: String) {
        chatViewModel.getMessages(sessionId).observe(this) { resultResponse ->
            when (resultResponse.status) {
                ResultResponse.Status.SUCCESS -> {
                    val serverResponse = resultResponse.data?.body()
                    if (serverResponse ?: "" != "") {
                        Log.e("success1 comments size", (serverResponse).toString())

                        if (serverResponse?.size ?: 0 > 0) {

                            mAdapter?.swap(serverResponse)
                            mAdapter?.notifyDataSetChanged()
                        }


                    } else {
                        toast("Something went wrong")
                    }

                    hideProgressDialog()
                }
                ResultResponse.Status.ERROR -> {
                    Log.e("failed", resultResponse.message.toString())
                    hideProgressDialog()
                }
                else -> {
                    Log.e("failed", resultResponse.message.toString())
                    hideProgressDialog()
                }
            }
        }
    }


    fun setImage(image: ImageView, imageUrl: String?) {
        if (image!=null&&!imageUrl.isNullOrEmpty()){
            val glideUrl = GlideUrl(
                imageUrl, LazyHeaders.Builder()
                    .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36"
                    )
                    .build()
            )

            Glide.with(image)
                .load(glideUrl)
                .placeholder(R.drawable.img_place_holder)
                .centerCrop()
                .into(image)
        }
    }

    //show progress dialog
    private fun showProgressDialog() {
        mProgressDialog =
            ProgressDialog(
                this,
                R.style.AppCompatAlertDialogStyle
            )
        mProgressDialog?.isIndeterminate = false
        mProgressDialog?.setCancelable(false)
        Utils.showProgressDialog(
            mProgressDialog,
            "",
            getString(R.string.please_wait)
        )
    }

    //hide progress dialog
    private fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog?.isShowing!!) {
            Utils.hideProgressDialog(mProgressDialog)
        }
    }
}