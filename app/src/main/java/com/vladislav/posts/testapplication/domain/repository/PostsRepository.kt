package com.vladislav.posts.testapplication.domain.repository

import com.vladislav.posts.testapplication.domain.model.Post
import com.vladislav.posts.testapplication.domain.model.PostDetails
import kotlinx.coroutines.flow.Flow

interface PostsRepository{

    suspend fun getAllPosts(): Flow<Result<List<Post>>>

    suspend fun getPostDetailsById(id: Long): Flow<PostDetails>

    suspend fun refreshPosts()

    suspend fun refreshPostDetails(id: Long)
}