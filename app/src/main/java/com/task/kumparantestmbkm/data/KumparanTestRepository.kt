package com.task.kumparantestmbkm.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.kumparantestmbkm.data.model.*
import com.task.kumparantestmbkm.data.source.RemoteDataSource

class KumparanTestRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    KumparanTestDataSource {


    override fun getPosts(): LiveData<List<PostsResponse>> {
        val responseServer = MutableLiveData<List<PostsResponse>>()
        remoteDataSource.getPosts(object : RemoteDataSource.LoadPostsCallback {
            override fun onLoadPosts(response: List<PostsResponse>?) {
                val postList = ArrayList<PostsResponse>()
                if (response != null) {
                    for (postResponse in response) {

                        val res = PostsResponse(
                            id = postResponse.id,
                            body = postResponse.body,
                            title = postResponse.title,
                            userId = postResponse.userId
                        )
                        postList.add(res)
                    }
                    responseServer.postValue(postList)
                }
            }
        })
        return responseServer
    }

    override fun getUsers(): LiveData<List<UserResponse>> {
        val userResponse = MutableLiveData<List<UserResponse>>()
        remoteDataSource.getUsers(object : RemoteDataSource.LoadUsersCallback {
            override fun onLoadUsers(response: List<UserResponse>?) {
                val postList = ArrayList<UserResponse>()
                if (response != null) {
                    for (userResponse in response) {

                        val res = UserResponse(
                            id = userResponse.id,
                            company = userResponse.company,
                            name = userResponse.name
                        )
                        postList.add(res)
                    }
                    userResponse.postValue(postList)
                }
            }
        })
        return userResponse
    }

    override fun getComments(idPost: Int): LiveData<List<CommentResponse>> {
        val commentResponse = MutableLiveData<List<CommentResponse>>()
        remoteDataSource.getComments(object : RemoteDataSource.LoadCommentsCallback {
            override fun onLoadComments(response: List<CommentResponse>?) {
                val commentsList = ArrayList<CommentResponse>()
                if (response != null) {
                    for (userResponse in response) {
                        if (userResponse.postId!! == idPost) {
                            val res = CommentResponse(
                                id = userResponse.id,
                                name = userResponse.name,
                                body = userResponse.body
                            )
                            commentsList.add(res)
                        }
                    }
                    commentResponse.postValue(commentsList)
                }
            }
        })
        return commentResponse
    }

    override fun getUserById(id: Int): LiveData<UserResponse> {
        val userResponse = MutableLiveData<UserResponse>()
        remoteDataSource.getUserByid(object : RemoteDataSource.LoadUserByIdCallback {
            override fun onLoadUserById(response: UserResponse?) {
                if (response != null) {
                    val res = UserResponse(
                        id = response.id,
                        address = response.address,
                        company = response.company,
                        email = response.email,
                        name = response.name
                    )
                    userResponse.postValue(res)
                }
            }

        }, id)
        return userResponse
    }

    override fun getAlbums(userId: Int): LiveData<List<AlbumResponse>> {
        val albumResponse = MutableLiveData<List<AlbumResponse>>()
        remoteDataSource.getAlbums(object : RemoteDataSource.LoadAlbumsCallback {
            override fun onLoadAlbums(response: List<AlbumResponse>?) {
                val commentsList = ArrayList<AlbumResponse>()
                if (response != null) {
                    for (albumResponse in response) {
                        if (albumResponse.userId == userId) {
                            val res = AlbumResponse(
                                id = albumResponse.id,
                                title = albumResponse.title,
                            )
                            commentsList.add(res)
                        }

                    }
                    albumResponse.postValue(commentsList)
                }
            }
        })
        return albumResponse
    }

    override fun getPhotos(albumId: Int): LiveData<List<PhotosResponse>> {
        val photosResponse = MutableLiveData<List<PhotosResponse>>()
        remoteDataSource.getPhotos(object : RemoteDataSource.LoadPohotosCallback {
            override fun onLoadPhotos(response: List<PhotosResponse>?) {
                val commentsList = ArrayList<PhotosResponse>()
                if (response != null) {
                    for (photosResponse in response) {
                        if (photosResponse.albumId == albumId) {
                            val res = PhotosResponse(
                                id = photosResponse.id,
                                title = photosResponse.title,
                                url =  photosResponse.url
                            )
                            commentsList.add(res)
                        }

                    }
                    photosResponse.postValue(commentsList)
                }
            }
        })
        return photosResponse
    }


    companion object {
        @Volatile
        private var insteance: KumparanTestRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): KumparanTestRepository =
            insteance ?: synchronized(this) {
                insteance ?: KumparanTestRepository(remoteDataSource).apply { insteance = this }
            }
    }

}