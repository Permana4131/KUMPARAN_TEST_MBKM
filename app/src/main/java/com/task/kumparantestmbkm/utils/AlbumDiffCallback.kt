package com.task.kumparantestmbkm.utils

import androidx.recyclerview.widget.DiffUtil
import com.task.kumparantestmbkm.data.model.AlbumResponse

class AlbumDiffCallback (private val mOldAlbum: List<AlbumResponse>, private val mNewAlbum: List<AlbumResponse>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldAlbum.size
    }

    override fun getNewListSize(): Int {
        return mNewAlbum.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldAlbum[oldItemPosition].id == mNewAlbum[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldAlokasi = mOldAlbum[oldItemPosition]
        val newAlokasi = mNewAlbum[newItemPosition]
        return oldAlokasi.id == newAlokasi.id && oldAlokasi.id == newAlokasi.id
    }
}