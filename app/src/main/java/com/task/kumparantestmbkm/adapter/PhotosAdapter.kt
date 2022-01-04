package com.task.kumparantestmbkm.adapter

import android.app.Dialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.task.kumparantestmbkm.data.model.PhotosResponse
import com.task.kumparantestmbkm.databinding.ItemListPhotoBinding
import com.task.kumparantestmbkm.utils.PhotosDiffCallback
import com.bumptech.glide.load.model.LazyHeaders

import com.bumptech.glide.load.model.GlideUrl
import com.task.kumparantestmbkm.R


class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {
    private var listPhoto = ArrayList<PhotosResponse>()
    fun setPhoto(listPhoto: List<PhotosResponse>) {
        val diffCallback = PhotosDiffCallback(this.listPhoto, listPhoto)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPhoto.clear()
        this.listPhoto.addAll(listPhoto)
        diffResult.dispatchUpdatesTo(this)
    }
    class PhotosViewHolder(var binding: ItemListPhotoBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = ItemListPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        val photo = listPhoto[position]
        val url = GlideUrl(
            photo.url, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(url)
                .into(holder.binding.imgPhotos)
        }


        holder.itemView.setOnClickListener {
            Dialog(holder.itemView.context).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setCancelable(true)
                setContentView(R.layout.activity_photo_detail)
                val title = this.findViewById<TextView>(R.id.tv_title)
                val img = this.findViewById<ImageView>(R.id.img_detail_photo)

                title.text = photo.title
                Glide.with(holder.itemView.context).load(url)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img)

            }.show()
        }
    }

    override fun getItemCount(): Int = listPhoto.size
}