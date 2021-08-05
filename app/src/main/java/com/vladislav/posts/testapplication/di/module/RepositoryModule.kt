package com.vladislav.posts.testapplication.di.module

import com.vladislav.posts.testapplication.data.repository.PostsRepositoryImpl
import com.vladislav.posts.testapplication.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindPostsRepository(impl: PostsRepositoryImpl): PostsRepository
}