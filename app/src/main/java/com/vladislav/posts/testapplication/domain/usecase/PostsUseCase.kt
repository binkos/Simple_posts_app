package com.vladislav.posts.testapplication.domain.usecase

import com.vladislav.posts.testapplication.domain.model.Post
import com.vladislav.posts.testapplication.domain.model.PostDetails
import kotlinx.coroutines.flow.Flow

interface PostsUseCase {

    suspend fun getPostDetailsByIds(postId: Long): Flow<PostDetails>

    suspend fun getAllPosts(): Flow<Result<List<Post>>>

    suspend fun refreshPosts()

    suspend fun refreshPostDetails(postId: Long)
}