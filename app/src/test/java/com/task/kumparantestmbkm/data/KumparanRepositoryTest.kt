package com.task.kumparantestmbkm.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.task.kumparantestmbkm.data.source.RemoteDataSource
import com.task.kumparantestmbkm.utils.DataDummy
import com.task.kumparantestmbkm.utils.LiveDataTestUtil
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class KumparanRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val kumparanRepository = FakeKumparanTestRpository(remote)

    private val dummyPosts = DataDummy.getDummyPosts()
    private val fakeIdPosts = 1
    private val fakeIdUser = 1
    private val fakeAlbumId = 1
    private val dummyCommentsFiletr = DataDummy.getDummyFillterId(fakeIdPosts)
    private val dummyComments = DataDummy.getDummyComments()
    private val dummyUsers = DataDummy.getDummyUsers()
    private val detailDummyUser = DataDummy.getDetailDummyUser()

    private val dummyAlbums = DataDummy.getDummyAlbums()
    private val dummAlbumsFillterById = DataDummy.getAlbumsFillterUserId(fakeIdUser)
    private val dummyPhotos = DataDummy.getDummyPhotos()
    private val dummyPhotosFillterByAlbumId = DataDummy.getPhotosFilterByAlbumId(fakeAlbumId)

    @Test
    fun getPosts(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadPostsCallback).onLoadPosts(dummyPosts)
            null
        }.`when`(remote).getPosts(any())
        val postsEntities = LiveDataTestUtil.getValue(kumparanRepository.getPosts())
        verify(remote).getPosts(any())
        Assert.assertNotNull(postsEntities)
        assertEquals(dummyPosts.size, postsEntities.size)
    }

    @Test
    fun getComments(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadCommentsCallback).onLoadComments(dummyComments)
            null
        }.`when`(remote).getComments(any())
        val commentsEntities = LiveDataTestUtil.getValue(kumparanRepository.getComments(fakeIdPosts))
        verify(remote).getComments(any())
        Assert.assertNotNull(commentsEntities)
        assertEquals(dummyCommentsFiletr.size, commentsEntities.size)
    }

    @Test
    fun getUsers(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadUsersCallback).onLoadUsers(dummyUsers)
            null
        }.`when`(remote).getUsers(any())
        val usersEntities = LiveDataTestUtil.getValue(kumparanRepository.getUsers())
        verify(remote).getUsers(any())
        Assert.assertNotNull(usersEntities)
        assertEquals(dummyUsers.size, usersEntities.size)
    }

    @Test
    fun getUsersById(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadUserByIdCallback).onLoadUserById(detailDummyUser)
            null
        }.`when`(remote).getUserByid(any(), eq(fakeIdUser))
        val usersEntities = LiveDataTestUtil.getValue(kumparanRepository.getUserById(fakeIdUser))
        verify(remote).getUserByid(any(), eq(fakeIdUser))
        Assert.assertNotNull(usersEntities)
        assertEquals(detailDummyUser.id, usersEntities.id)
        assertEquals(detailDummyUser.name, usersEntities.name)
        assertEquals(detailDummyUser.email, usersEntities.email)
        assertEquals(detailDummyUser.username, usersEntities.username)
        assertEquals(detailDummyUser.company, usersEntities.company)
        assertEquals(detailDummyUser.address, usersEntities.address)
    }

    @Test
    fun getAlbums(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadAlbumsCallback).onLoadAlbums(dummyAlbums)
            null
        }.`when`(remote).getAlbums(any())
        val albumsEntities = LiveDataTestUtil.getValue(kumparanRepository.getAlbums(fakeIdUser))
        verify(remote).getAlbums(any())
        Assert.assertNotNull(albumsEntities)
        assertEquals(dummAlbumsFillterById.size, albumsEntities.size)
    }

    @Test
    fun getPhotos(){
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadPohotosCallback).onLoadPhotos(dummyPhotos)
            null
        }.`when`(remote).getPhotos(any())
        val photosEntity = LiveDataTestUtil.getValue(kumparanRepository.getPhotos(fakeAlbumId))
        verify(remote).getPhotos(any())
        Assert.assertNotNull(photosEntity)
        assertEquals(dummyPhotosFillterByAlbumId.size, photosEntity.size)
    }
}