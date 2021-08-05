package com.vladislav.posts.testapplication.data.mapper

import com.vladislav.posts.testapplication.data.db.entity.PostEntity
import com.vladislav.posts.testapplication.data.db.entity.UserEntity
import com.vladislav.posts.testapplication.data.network.entity.User
import com.vladislav.posts.testapplication.domain.model.Post
import com.vladislav.posts.testapplication.data.network.entity.Post as DataPost

fun PostEntity.mapToDomain(): Post = Post(id, title)

fun User.toDb(): UserEntity = UserEntity(id, name, id.toAvatarLink())

fun DataPost.toDb(): PostEntity = PostEntity(id, userId, title, title)

private fun Long.toAvatarLink(): String =
    "https://source.unsplash.com/collection/542909/?sig=$this"