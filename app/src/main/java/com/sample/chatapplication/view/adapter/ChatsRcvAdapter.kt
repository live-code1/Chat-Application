package com.sample.chatapplication.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.chatapplication.R
import com.sample.chatapplication.data.model.Message
import com.sample.chatapplication.databinding.ItemChatReceiveBinding
import com.sample.chatapplication.databinding.ItemChatSendBinding
import com.sample.chatapplication.util.Constants

class ChatsRcvAdapter(
    private var ctx: Activity
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val message: MutableList<Message> = ArrayList()

    inner class SendViewHolder(private var binding: ItemChatSendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.message = message


        }
    }

    inner class ReceiveViewHolder(private var binding: ItemChatReceiveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.message = message


        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        if (viewType ==Constants.ITEM_SEND)
            {
                return SendViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_chat_send,
                        parent,
                        false
                    )
                )
            }
            else{
                return ReceiveViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.item_chat_receive,
                        parent,
                        false
                    )
                )
            }

    }


    override fun getItemCount(): Int {
        return message.size
    }


    override fun getItemViewType(position: Int): Int {
        return  if (message[position].messageType==0){
               Constants.ITEM_SEND
        }
        else{
               Constants.ITEM_RECEIVE
        }

    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(holder.adapterPosition)){
            Constants.ITEM_SEND->{
                val sendHolder: SendViewHolder = holder as SendViewHolder
                sendHolder.bind(message[holder.adapterPosition])
            }
            Constants.ITEM_RECEIVE->{
                val receiveHolder: ReceiveViewHolder = holder as ReceiveViewHolder
                receiveHolder.bind(message[holder.adapterPosition])
            }
        }

    }

    //setting data to the mutable list
    fun swap(fruits: MutableList<Message>?) {

        val diffCallback =
            ChatSingleConversationListDiffUtils(
                this.message,
                fruits ?: ArrayList()
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.message.clear()
        this.message.addAll(fruits ?: ArrayList())
        diffResult.dispatchUpdatesTo(this)
    }


}
//DiffUtil is a utility class that calculates the difference between two lists and
// outputs a list of update operations that converts the first list into the second one.
private class ChatSingleConversationListDiffUtils(
    private val oldList: MutableList<Message>, private val newList: MutableList<Message>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].messageId == newList[newItemPosition].messageId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].messageId == newList[newItemPosition].messageId

    }
}

