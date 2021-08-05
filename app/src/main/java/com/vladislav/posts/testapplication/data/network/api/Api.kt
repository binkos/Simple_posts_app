package com.vladislav.posts.testapplication.data.network.api

import com.vladislav.posts.testapplication.data.network.entity.Post
import com.vladislav.posts.testapplication.data.network.entity.User
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/posts")
    suspend fun getAllPosts(): List<Post>

    @GET("/posts/{id}")
    suspend fun getPostById(@Path("id") id: Long): Post

    @GET("/users/{id}")
    suspend fun getUserById(@Path("id") id: Long): User
}