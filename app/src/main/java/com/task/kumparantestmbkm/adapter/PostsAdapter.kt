package com.task.kumparantestmbkm.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.kumparantestmbkm.data.model.PostsResponse
import com.task.kumparantestmbkm.data.model.UserResponse
import com.task.kumparantestmbkm.databinding.ItemListPostsBinding
import com.task.kumparantestmbkm.ui.posts.DetailPostActivity
import com.task.kumparantestmbkm.utils.PostsDiffCallback

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var listPost = ArrayList<PostsResponse>()
    private var listUser = ArrayList<UserResponse>()

    fun setPost(listPost: List<PostsResponse>) {
        val diffCallback = PostsDiffCallback(this.listPost, listPost)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPost.clear()
        this.listPost.addAll(listPost)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setUser(users: List<UserResponse>) {
        this.listUser.clear()
        this.listUser.addAll(users)
    }

    class PostsViewHolder(var binding: ItemListPostsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = ItemListPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = listPost[position]
        for (user in listUser) {
            if (post.userId!! == user.id) {
                holder.binding.apply {
                    tvItemTitle.text = post.title
                    tvItemBody.text = post.body
                    tvItemUsername.text = user.name
                    tvItemCompany.text = user.company?.name
                    holder.itemView.setOnClickListener {
                        val intent = Intent(holder.itemView.context, DetailPostActivity::class.java)
                        intent.putExtra(DetailPostActivity.EXTRA_POST, post)
                        intent.putExtra(DetailPostActivity.EXTRA_USER, user.name)
                        holder.itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listPost.size
}