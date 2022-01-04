package com.task.kumparantestmbkm.utils

import com.task.kumparantestmbkm.data.KumparanTestRepository
import com.task.kumparantestmbkm.data.source.RemoteDataSource

object Injection {
    fun provideRepository(): KumparanTestRepository{
        val remoteDataSource = RemoteDataSource.getInstance()
        return KumparanTestRepository.getInstance(remoteDataSource)
    }
}