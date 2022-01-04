package com.task.kumparantestmbkm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.kumparantestmbkm.data.model.CommentResponse
import com.task.kumparantestmbkm.databinding.ItemListCommentBinding
import com.task.kumparantestmbkm.utils.CommentsDiffCallback

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    private var listComment = ArrayList<CommentResponse>()
    fun setComment(listComment: List<CommentResponse>) {
        val diffCallback = CommentsDiffCallback(this.listComment, listComment)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listComment.clear()
        this.listComment.addAll(listComment)
        diffResult.dispatchUpdatesTo(this)
    }
    class CommentsViewHolder(var binding: ItemListCommentBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val view = ItemListCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
       val comment = listComment[position]
        holder.binding.apply {
            tvItemCommentName.text = comment.name
            tvItemCommentBody.text = comment.body
        }
    }

    override fun getItemCount(): Int = listComment.size
}