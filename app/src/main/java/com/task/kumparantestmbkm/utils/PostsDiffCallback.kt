package com.task.kumparantestmbkm.utils

import androidx.recyclerview.widget.DiffUtil
import com.task.kumparantestmbkm.data.model.PostsResponse

class PostsDiffCallback (private val mOldPost: List<PostsResponse>, private val mNewPost: List<PostsResponse>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPost.size
    }

    override fun getNewListSize(): Int {
        return mNewPost.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPost[oldItemPosition].id == mNewPost[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAlokasi = mOldPost[oldItemPosition]
        val newAlokasi = mNewPost[newItemPosition]
        return oldAlokasi.id == newAlokasi.id && oldAlokasi.id == newAlokasi.id
    }
}