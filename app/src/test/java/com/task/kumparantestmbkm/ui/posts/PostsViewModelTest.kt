package com.task.kumparantestmbkm.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.task.kumparantestmbkm.data.KumparanTestRepository
import com.task.kumparantestmbkm.data.model.PostsResponse
import com.task.kumparantestmbkm.utils.DataDummy
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.task.kumparantestmbkm.data.model.CommentResponse
import com.task.kumparantestmbkm.data.model.UserResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@RunWith(MockitoJUnitRunner::class)
class PostsViewModelTest {
    private lateinit var viewModel : PostsViewModel

    @Mock
    private var kumparanRepository = mock(KumparanTestRepository::class.java)

    @Mock
    private lateinit var observerPosts: Observer<List<PostsResponse>>

    @Mock
    private lateinit var observerUser: Observer<List<UserResponse>>

    @Mock
    private lateinit var observerComments: Observer<List<CommentResponse>>

    @get:Rule
    var instantTaskExecutorRule  = InstantTaskExecutorRule()


    @Before
    fun setUp(){
        viewModel = PostsViewModel(kumparanRepository)
    }


    @Test
    fun getPosts(){
        val dummyPosts = DataDummy.getDummyPosts()
        val posts = MutableLiveData<List<PostsResponse>>()
        posts.value = dummyPosts

        Mockito.`when`(kumparanRepository.getPosts()).thenReturn(posts)
        val postsData = viewModel.getPosts().value
        verify(kumparanRepository).getPosts()
        Assert.assertNotNull(postsData)
        Assert.assertEquals(dummyPosts.size, postsData?.size)

        viewModel.getPosts().observeForever(observerPosts)
        verify(observerPosts).onChanged(dummyPosts)
    }

    @Test
    fun getUser(){
        val dummyUser = DataDummy.getDummyUsers()
        val users = MutableLiveData<List<UserResponse>>()
        users.value = dummyUser

        Mockito.`when`(kumparanRepository.getUsers()).thenReturn(users)
        val userData = viewModel.getUsers().value
        verify(kumparanRepository).getUsers()
        Assert.assertNotNull(userData)
        Assert.assertEquals(dummyUser.size, userData?.size)

        viewModel.getUsers().observeForever(observerUser)
        verify(observerUser).onChanged(dummyUser)
    }

    @Test
    fun getComment(){
        val dummyComment = DataDummy.getDummyComments()
        val comments = MutableLiveData<List<CommentResponse>>()
        comments.value = dummyComment

        Mockito.`when`(kumparanRepository.getComments(1)).thenReturn(comments)
        val commentsData = viewModel.getComments(1).value
        verify(kumparanRepository).getComments(1)
        Assert.assertNotNull(commentsData)
        Assert.assertEquals(dummyComment.size, commentsData?.size)

        viewModel.getComments(1).observeForever(observerComments)
        verify(observerComments).onChanged(dummyComment)
    }



}