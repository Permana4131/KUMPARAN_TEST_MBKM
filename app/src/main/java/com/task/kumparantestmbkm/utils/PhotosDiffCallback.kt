package com.task.kumparantestmbkm.utils

import androidx.recyclerview.widget.DiffUtil
import com.task.kumparantestmbkm.data.model.PhotosResponse

class PhotosDiffCallback (private val mOldPhoto: List<PhotosResponse>, private val mNewPhoto: List<PhotosResponse>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPhoto.size
    }

    override fun getNewListSize(): Int {
        return mNewPhoto.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPhoto[oldItemPosition].id == mNewPhoto[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAlokasi = mOldPhoto[oldItemPosition]
        val newAlokasi = mNewPhoto[newItemPosition]
        return oldAlokasi.id == newAlokasi.id && oldAlokasi.id == newAlokasi.id
    }
}