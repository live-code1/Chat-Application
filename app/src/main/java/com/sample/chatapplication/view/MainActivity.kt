package com.sample.chatapplication.view

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.chatapplication.BaseActivity
import com.sample.chatapplication.R
import com.sample.chatapplication.data.network.ResultResponse
import com.sample.chatapplication.util.AppUtils
import com.sample.chatapplication.util.Utils
import com.sample.chatapplication.util.toast
import com.sample.chatapplication.view.adapter.UsersRcvAdapter
import com.sample.chatapplication.viewmodel.ChatViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val chatViewModel: ChatViewModel by viewModels()
    var mAdapter: UsersRcvAdapter? = null
    private var mProgressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showProgressDialog()
        rcv_contacts.layoutManager= LinearLayoutManager(this)
        mAdapter= UsersRcvAdapter(this)
        rcv_contacts.adapter=mAdapter

        if(AppUtils.isNetworkAvailable(this)){
            getUsers()
        }
        else{
            toast(getString(R.string.no_network))
        }


    }

    //get users from server
    private fun getUsers() {
        chatViewModel.getUsers().observe(this) { resultResponse ->
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