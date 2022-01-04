package com.task.kumparantestmbkm.ui.posts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.kumparantestmbkm.adapter.CommentsAdapter
import com.task.kumparantestmbkm.data.model.PostsResponse
import com.task.kumparantestmbkm.databinding.ActivityDetailPostBinding
import com.task.kumparantestmbkm.ui.profil.ProfilActivity
import com.task.kumparantestmbkm.utils.ViewModelFactory

class DetailPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = intent.getParcelableExtra<PostsResponse>(EXTRA_POST)
        val username = intent.getStringExtra(EXTRA_USER).toString()

        supportActionBar?.title = "Detail Post"
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailAdapter = CommentsAdapter()

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[PostsViewModel::class.java]

        binding.apply {
            tvItemDetailTitle.text = post?.title
            tvItemDetailBody.text = post?.body
            tvItemDetailUsername.text = username
            tvItemDetailUsername.setOnClickListener {
                val intent = Intent(this@DetailPostActivity, ProfilActivity::class.java)
                intent.putExtra(ProfilActivity.EXTRA_USER, post?.userId)
                startActivity(intent)
            }

            progressBar.visibility = View.VISIBLE
        }

        viewModel.getComments(post?.id!!).observe(this, { comments ->
            if (comments != null) {
                detailAdapter.setComment(comments)
            }
            binding.progressBar.visibility = View.GONE
        })

        with(binding.rvComment) {
            layoutManager = LinearLayoutManager(context)
            adapter = detailAdapter
            isNestedScrollingEnabled = false
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_POST = "extra_post"
        const val EXTRA_USER = "extra_user"
    }
}