package com.task.kumparantestmbkm.data.source

import android.util.Log
import com.task.kumparantestmbkm.data.model.PostsResponse
import com.task.kumparantestmbkm.data.model.AlbumResponse
import com.task.kumparantestmbkm.data.model.CommentResponse
import com.task.kumparantestmbkm.data.model.PhotosResponse
import com.task.kumparantestmbkm.data.model.UserResponse
import com.task.kumparantestmbkm.network.ApiConfig
import com.task.kumparantestmbkm.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getPosts(callback: LoadPostsCallback){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServices().getAllPost()
            .enqueue(object : Callback<List<PostsResponse>> {
                override fun onResponse(
                    call: Call<List<PostsResponse>>,
                    response: Response<List<PostsResponse>>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadPosts(response.body())
                        Log.d("succes", response.code().toString())
                        EspressoIdlingResource.decrement()
                    }else{
                        Log.d("fail", response.message())
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<List<PostsResponse>>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getUsers(callback: LoadUsersCallback){
        ApiConfig.getApiServices().getUsers()
            .enqueue(object : Callback<List<UserResponse>> {
                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadUsers(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }


            })
    }

    fun getComments(callback: LoadCommentsCallback){
        ApiConfig.getApiServices().getComments()
            .enqueue(object : Callback<List<CommentResponse>>{
                override fun onResponse(
                    call: Call<List<CommentResponse>>,
                    response: Response<List<CommentResponse>>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadComments(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<List<CommentResponse>>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun getUserByid(callback: LoadUserByIdCallback, id: Int){
        ApiConfig.getApiServices().getUserById(id)
            .enqueue(object :Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadUserById(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }

    fun getAlbums(callback: LoadAlbumsCallback){
        ApiConfig.getApiServices().getAlbums()
            .enqueue(object : Callback<List<AlbumResponse>>{
                override fun onResponse(
                    call: Call<List<AlbumResponse>>,
                    response: Response<List<AlbumResponse>>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadAlbums(response.body())
                        Log.d("succes", response.code().toString())
                    }else{
                        Log.d("fail", response.message())
                    }
                }

                override fun onFailure(call: Call<List<AlbumResponse>>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }


    fun getPhotos(callback: LoadPohotosCallback){
        EspressoIdlingResource.increment()
        ApiConfig.getApiServices().getPhotos()
            .enqueue(object : Callback<List<PhotosResponse>>{
                override fun onResponse(
                    call: Call<List<PhotosResponse>>,
                    response: Response<List<PhotosResponse>>
                ) {
                    if (response.isSuccessful){
                        callback.onLoadPhotos(response.body())
                        Log.d("succes", response.code().toString())
                        EspressoIdlingResource.decrement()
                    }else{
                        Log.d("fail", response.message())
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<List<PhotosResponse>>, t: Throwable) {
                    Log.d("fail", t.message.toString())
                }

            })
    }


    interface LoadPostsCallback{
        fun onLoadPosts(response: List<PostsResponse>?)
    }

    interface LoadUsersCallback{
        fun onLoadUsers(response: List<UserResponse>?)
    }

    interface LoadCommentsCallback{
        fun onLoadComments(response: List<CommentResponse>?)
    }

    interface LoadUserByIdCallback{
        fun onLoadUserById(response: UserResponse?)
    }

    interface LoadAlbumsCallback{
        fun onLoadAlbums(response: List<AlbumResponse>?)
    }

    interface LoadPohotosCallback{
        fun onLoadPhotos(response: List<PhotosResponse>?)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource?=null

        fun getInstance(): RemoteDataSource =
            instance?: synchronized(this){
                instance?: RemoteDataSource()
            }
    }
}