package com.task.kumparantestmbkm.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.kumparantestmbkm.data.model.*
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

object DataDummy {
    private var data = Gson()

    private var listPost: Type = object : TypeToken<List<PostsResponse?>?>() {}.type
    fun getDummyPosts() = data.fromJson(loadJSON("posts.json"), listPost) as (List<PostsResponse>)

    private var listComment: Type = object : TypeToken<List<CommentResponse?>?>() {}.type
    fun getDummyComments() =
        data.fromJson(loadJSON("comments.json"), listComment) as (List<CommentResponse>)

    fun getDummyFillterId(idPost: Int): ArrayList<CommentResponse> {
        val dummyComment: ArrayList<CommentResponse> = arrayListOf()
        getDummyComments().forEach { dummyComments ->
            if (dummyComments.postId!! == idPost) {
                dummyComment.add(dummyComments)
            }
        }
        return dummyComment
    }


    private var listUser: Type = object : TypeToken<List<UserResponse?>?>() {}.type
    fun getDummyUsers() = data.fromJson(loadJSON("users.json"), listUser) as (List<UserResponse>)

    fun getDetailDummyUser(): UserResponse = data.fromJson(loadJSON("detailUser.json"), UserResponse::class.java)

    private var listAlbum: Type = object : TypeToken<List<AlbumResponse?>?>() {}.type
    fun getDummyAlbums() =
        data.fromJson(loadJSON("albums.json"), listAlbum) as (List<AlbumResponse>)

    fun getAlbumsFillterUserId(idUser: Int): ArrayList<AlbumResponse> {
        val arrAlbums: ArrayList<AlbumResponse> = arrayListOf()
        getDummyAlbums().forEach { albums ->
            if (albums.userId == idUser) {
                arrAlbums.add(albums)
            }
        }
        return arrAlbums
    }

    private var listPhoto: Type = object : TypeToken<List<PhotosResponse?>?>() {}.type
    fun getDummyPhotos() =
        data.fromJson(loadJSON("photos.json"), listPhoto) as (List<PhotosResponse>)

    fun getPhotosFilterByAlbumId(albumId: Int): ArrayList<PhotosResponse> {
        val arrPhotos: ArrayList<PhotosResponse> = arrayListOf()
        getDummyPhotos().forEach { photos ->
            if (photos.albumId == albumId) {
                arrPhotos.add(photos)
            }
        }

        println("size "+arrPhotos.size)
        return arrPhotos
    }

    private fun loadJSON(filename: String): String? {
        var json: String? = null
        try {
            val input: InputStream = this.javaClass.classLoader!!.getResourceAsStream(filename)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer, charset("UTF-8"))
        } catch (ex: IOException) {
            Log.e("Dummy", ex.toString())
        }

        return json
    }
}