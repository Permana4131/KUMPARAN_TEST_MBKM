package com.task.kumparantestmbkm.ui.profil

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.kumparantestmbkm.adapter.AlbumAdapter
import com.task.kumparantestmbkm.databinding.ActivityProfilBinding
import com.task.kumparantestmbkm.utils.ViewModelFactory

class ProfilActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[ProfilViewModel::class.java]


        supportActionBar?.title = "Detail User"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val idUser = intent.getIntExtra(EXTRA_USER, 0)

        val adapterAlbum = AlbumAdapter()

        binding.progressBar.visibility =  View.VISIBLE

        viewModel.getUserByiD(idUser).observe(this, {user->
            binding.apply {
                tvItemProfilAdress.text = "Adress : ${user.address?.street} ${user.address?.suite}, ${user.address?.city}"
                tvItemProfilCompany.text = "Company : ${user.company?.name}"
                tvItemProfilEmail.text = user.email
                tvItemProfilName.text = user.name
            }
        })

        viewModel.getAlbums(idUser).observe(this, {albums->
            if (albums!=null){
                adapterAlbum.setAlbum(albums)
            }
            binding.progressBar.visibility =  View.GONE
        })

        with(binding.rvAlbum){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterAlbum
        }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}