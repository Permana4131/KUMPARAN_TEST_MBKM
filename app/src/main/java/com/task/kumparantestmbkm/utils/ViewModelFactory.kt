package com.task.kumparantestmbkm.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.kumparantestmbkm.data.KumparanTestRepository
import com.task.kumparantestmbkm.ui.posts.PostsViewModel
import com.task.kumparantestmbkm.ui.profil.ProfilViewModel

class ViewModelFactory private constructor(private val mKumparanTestRepository: KumparanTestRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(PostsViewModel::class.java) -> {
                PostsViewModel(mKumparanTestRepository) as T
            }

            modelClass.isAssignableFrom(ProfilViewModel::class.java) -> {
                ProfilViewModel(mKumparanTestRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }


    companion object{
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository()).apply {
                    instance = this
                }
            }
    }
}