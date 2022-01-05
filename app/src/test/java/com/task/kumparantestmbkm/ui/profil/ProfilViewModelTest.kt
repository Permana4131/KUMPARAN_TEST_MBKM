package com.task.kumparantestmbkm.ui.profil

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.task.kumparantestmbkm.data.KumparanTestRepository
import com.task.kumparantestmbkm.data.model.AlbumResponse
import com.task.kumparantestmbkm.data.model.PhotosResponse
import com.task.kumparantestmbkm.data.model.UserResponse
import com.task.kumparantestmbkm.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProfilViewModelTest {

    private lateinit var viewModel: ProfilViewModel

    @Mock
    private var kumparanRepository = Mockito.mock(KumparanTestRepository::class.java)

    @Mock
    private lateinit var observerUser: Observer<UserResponse>

    @Mock
    private lateinit var observerAlbums: Observer<List<AlbumResponse>>

    @Mock
    private lateinit var observerPhotos: Observer<List<PhotosResponse>>


    @get:Rule
    var instantTaskExecutorRule  = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        viewModel = ProfilViewModel(kumparanRepository)
    }

    @Test
    fun getUserByID(){
        val dummyUser = DataDummy.getDetailDummyUser()
        val users = MutableLiveData<UserResponse>()
        users.value = dummyUser

        Mockito.`when`(kumparanRepository.getUserById(1)).thenReturn(users)
        val userData = viewModel.getUserByiD(1).value
        verify(kumparanRepository).getUserById(1)
        Assert.assertNotNull(viewModel.getUserByiD(1))
        Assert.assertEquals(dummyUser.id, userData?.id)
        Assert.assertEquals(dummyUser.company, userData?.company)
        Assert.assertEquals(dummyUser.address, userData?.address)
        Assert.assertEquals(dummyUser.username, userData?.username)
        Assert.assertEquals(dummyUser.email, userData?.email)


        viewModel.getUserByiD(1).observeForever(observerUser)
        verify(observerUser).onChanged(dummyUser)
    }

    @Test
    fun getAlbums(){
        val dummyAlbums = DataDummy.getDummyAlbums()
        val albums = MutableLiveData<List<AlbumResponse>>()
        albums.value = dummyAlbums

        Mockito.`when`(kumparanRepository.getAlbums(1)).thenReturn(albums)
        val albumsData = viewModel.getAlbums(1).value
        verify(kumparanRepository).getAlbums(1)
        Assert.assertNotNull(albumsData)
        Assert.assertEquals(dummyAlbums.size, albumsData?.size)

        viewModel.getAlbums(1).observeForever(observerAlbums)
        verify(observerAlbums).onChanged(dummyAlbums)
    }

    @Test
    fun getPhotos(){
        val dummyPhotos = DataDummy.getDummyPhotos()
        val photos = MutableLiveData<List<PhotosResponse>>()
        photos.value = dummyPhotos

        Mockito.`when`(kumparanRepository.getPhotos(1)).thenReturn(photos)
        val photosData = viewModel.getPhotos(1).value
        verify(kumparanRepository).getPhotos(1)
        Assert.assertNotNull(photosData)
        Assert.assertEquals(dummyPhotos.size, photosData?.size)

        viewModel.getPhotos(1).observeForever(observerPhotos)
        verify(observerPhotos).onChanged(dummyPhotos)
    }



}