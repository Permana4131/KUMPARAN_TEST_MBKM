package com.task.kumparantestmbkm.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.kumparantestmbkm.adapter.PostsAdapter
import com.task.kumparantestmbkm.databinding.ActivityPostsBinding
import com.task.kumparantestmbkm.utils.ViewModelFactory

class PostsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "All Post"

        val postAdapter = PostsAdapter()

        binding.progressBar.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this, factory)[PostsViewModel::class.java]

        viewModel.getPosts().observe(this, {posts->
            if (posts!=null){
                postAdapter.setPost(posts)
            }
        })
        viewModel.getUsers().observe(this, {users->
            if (users!=null){
                postAdapter.setUser(users)
                binding.progressBar.visibility = View.GONE
            }
        })

        with(binding.rvPosts){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }

    }

}