package com.task.kumparantestmbkm.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.task.kumparantestmbkm.data.KumparanTestRepository
import com.task.kumparantestmbkm.data.model.AlbumResponse
import com.task.kumparantestmbkm.data.model.PhotosResponse
import com.task.kumparantestmbkm.data.model.UserResponse

class ProfilViewModel(private val kumparanTestRepository: KumparanTestRepository) : ViewModel() {
    fun getUserByiD(id: Int) : LiveData<UserResponse> = kumparanTestRepository.getUserById(id)

    fun getAlbums(userId: Int) : LiveData<List<AlbumResponse>> = kumparanTestRepository.getAlbums(userId)

    fun getPhotos(albumId: Int): LiveData<List<PhotosResponse>> = kumparanTestRepository.getPhotos(albumId)

}