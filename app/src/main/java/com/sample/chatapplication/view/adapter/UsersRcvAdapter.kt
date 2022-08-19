package com.sample.chatapplication.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.chatapplication.R
import com.sample.chatapplication.data.model.User
import com.sample.chatapplication.databinding.ItemUserBinding
import com.sample.chatapplication.view.ChatActivity

class UsersRcvAdapter(
    private var ctx: Activity
) :
    RecyclerView.Adapter<UsersRcvAdapter.ViewHolder>() {
    private val user: MutableList<User> = ArrayList()

    inner class ViewHolder(private var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.root.setOnClickListener {
                val intent=Intent(ctx, ChatActivity::class.java)
                intent.putExtra("userName", user.userName)
                intent.putExtra("userId", user.userId.toString())
                intent.putExtra("userImage", user.imageUrl)
                ctx.startActivity(intent)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user,
                parent,
                false
            )
        )
    }


    override fun getItemCount(): Int {
        return user.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(user[holder.adapterPosition])
    }

    //setting data to the mutable list
    fun swap(fruits: MutableList<User>?) {

        val diffCallback =
            UserSingleConversationListDiffUtils(
                this.user,
                fruits ?: ArrayList()
            )
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.user.clear()
        this.user.addAll(fruits ?: ArrayList())
        diffResult.dispatchUpdatesTo(this)
    }


}
//DiffUtil is a utility class that calculates the difference between two lists and
// outputs a list of update operations that converts the first list into the second one.
private class UserSingleConversationListDiffUtils(
    private val oldList: MutableList<User>, private val newList: MutableList<User>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userId == newList[newItemPosition].userId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userId == newList[newItemPosition].userId

    }
}

