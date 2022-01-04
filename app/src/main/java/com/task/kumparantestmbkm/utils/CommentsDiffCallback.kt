package com.task.kumparantestmbkm.utils

import androidx.recyclerview.widget.DiffUtil
import com.task.kumparantestmbkm.data.model.CommentResponse

class CommentsDiffCallback (private val mOldComment: List<CommentResponse>, private val mNewComment: List<CommentResponse>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldComment.size
    }

    override fun getNewListSize(): Int {
        return mNewComment.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldComment[oldItemPosition].id == mNewComment[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAlokasi = mOldComment[oldItemPosition]
        val newAlokasi = mNewComment[newItemPosition]
        return oldAlokasi.id == newAlokasi.id && oldAlokasi.id == newAlokasi.id
    }
}