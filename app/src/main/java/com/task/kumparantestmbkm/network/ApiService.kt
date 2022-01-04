package com.task.kumparantestmbkm.network

import com.task.kumparantestmbkm.data.model.PostsResponse
import com.task.kumparantestmbkm.data.model.AlbumResponse
import com.task.kumparantestmbkm.data.model.CommentResponse
import com.task.kumparantestmbkm.data.model.PhotosResponse
import com.task.kumparantestmbkm.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("posts")
    fun getAllPost() : Call<List<PostsResponse>>

    @GET("users")
    fun getUsers(
    ): Call<List<UserResponse>>

    @GET("comments")
    fun getComments() : Call<List<CommentResponse>>

    @GET("users/{id}")
    fun getUserById(
        @Path("id") id: Int
    ) : Call<UserResponse>

    @GET("albums")
    fun getAlbums(): Call<List<AlbumResponse>>

    @GET("photos")
    fun getPhotos(): Call<List<PhotosResponse>>

}