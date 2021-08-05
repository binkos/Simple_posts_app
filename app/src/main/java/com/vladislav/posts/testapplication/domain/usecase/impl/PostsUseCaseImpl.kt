package com.vladislav.posts.testapplication.domain.usecase.impl

import com.vladislav.posts.testapplication.core.dispatchers.DispatchersProvider
import com.vladislav.posts.testapplication.domain.repository.PostsRepository
import com.vladislav.posts.testapplication.domain.model.Post
import com.vladislav.posts.testapplication.domain.model.PostDetails
import com.vladislav.posts.testapplication.domain.usecase.PostsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostsUseCaseImpl @Inject constructor(
    private val repository: PostsRepository,
    private val dispatchers: DispatchersProvider
) : PostsUseCase {

    override suspend fun getPostDetailsByIds(postId: Long): Flow<PostDetails> =
        withContext(dispatchers.io) { repository.getPostDetailsById(postId) }

    override suspend fun getAllPosts(): Flow<Result<List<Post>>> =
        withContext(dispatchers.io) { repository.getAllPosts() }

    override suspend fun refreshPosts() {
        withContext(dispatchers.io) { repository.refreshPosts() }
    }

    override suspend fun refreshPostDetails(postId: Long) {
        repository.refreshPostDetails(postId)
    }
}