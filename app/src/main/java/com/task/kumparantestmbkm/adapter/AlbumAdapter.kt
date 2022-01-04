package com.task.kumparantestmbkm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.kumparantestmbkm.data.model.AlbumResponse
import com.task.kumparantestmbkm.databinding.ItemListAlbumBinding
import com.task.kumparantestmbkm.ui.profil.PhotosActivity
import com.task.kumparantestmbkm.utils.AlbumDiffCallback

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {
    private var listAlbum = ArrayList<AlbumResponse>()
    fun setAlbum(listAlbum: List<AlbumResponse>) {
        val diffCallback = AlbumDiffCallback(this.listAlbum, listAlbum)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listAlbum.clear()
        this.listAlbum.addAll(listAlbum)
        diffResult.dispatchUpdatesTo(this)
    }

    class AlbumViewHolder(var binding: ItemListAlbumBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = ItemListAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = listAlbum[position]
        holder.binding.tvItemAlbum.text = album.title

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PhotosActivity::class.java)
            intent.putExtra(PhotosActivity.ID_ALBUM, album.id)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listAlbum.size
}