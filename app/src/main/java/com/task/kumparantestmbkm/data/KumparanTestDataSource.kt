package com.task.kumparantestmbkm.data

import androidx.lifecycle.LiveData
import com.task.kumparantestmbkm.data.model.*

interface KumparanTestDataSource {
    fun getPosts() : LiveData<List<PostsResponse>>

    fun getUsers() : LiveData<List<UserResponse>>

    fun getComments(idPost: Int) : LiveData<List<CommentResponse>>

    fun getUserById(id: Int) : LiveData<UserResponse>

    fun getAlbums(userId: Int): LiveData<List<AlbumResponse>>

    fun getPhotos(albumId: Int): LiveData<List<PhotosResponse>>
}