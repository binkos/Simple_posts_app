package com.vladislav.posts.testapplication.data.network.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName("id")
    val id: Long,
    @SerialName("userId")
    val userId: Long,
    @SerialName("title")
    val title: String,
    @SerialName("body")
    val body: String
)