package com.task.kumparantestmbkm.ui.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.task.kumparantestmbkm.data.KumparanTestRepository
import com.task.kumparantestmbkm.data.model.CommentResponse
import com.task.kumparantestmbkm.data.model.PostsResponse
import com.task.kumparantestmbkm.data.model.UserResponse

class PostsViewModel(private val kumparanTestRepository: KumparanTestRepository) : ViewModel() {
    fun getPosts() : LiveData<List<PostsResponse>> = kumparanTestRepository.getPosts()

    fun getUsers() : LiveData<List<UserResponse>> = kumparanTestRepository.getUsers()

    fun getComments(idPost: Int) : LiveData<List<CommentResponse>> = kumparanTestRepository.getComments(idPost)
}