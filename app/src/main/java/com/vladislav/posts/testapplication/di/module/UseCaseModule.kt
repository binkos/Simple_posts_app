package com.vladislav.posts.testapplication.di.module

import com.vladislav.posts.testapplication.domain.usecase.PostsUseCase
import com.vladislav.posts.testapplication.domain.usecase.impl.PostsUseCaseImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCaseModule {

    @Binds
    fun bindPostsUseCase(impl: PostsUseCaseImpl): PostsUseCase
}