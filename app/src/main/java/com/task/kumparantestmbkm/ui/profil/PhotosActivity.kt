package com.task.kumparantestmbkm.ui.profil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.task.kumparantestmbkm.adapter.PhotosAdapter
import com.task.kumparantestmbkm.databinding.ActivityPhotosBinding
import com.task.kumparantestmbkm.utils.ViewModelFactory

class PhotosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPhotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Photos User"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ProfilViewModel::class.java]

        val idAlbum = intent.getIntExtra(ID_ALBUM, 0)

        val photosAdapter = PhotosAdapter()

        viewModel.getPhotos(idAlbum).observe(this, {photos->
            photosAdapter.setPhoto(photos)
            println(photos)
        })

        with(binding.rvPhotos){
            layoutManager = GridLayoutManager(context, 3)
            adapter = photosAdapter
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object{
        const val ID_ALBUM = "id_album"
    }
}