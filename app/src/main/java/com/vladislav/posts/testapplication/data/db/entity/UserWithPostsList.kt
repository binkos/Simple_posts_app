package com.vladislav.posts.testapplication.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class UserWithPostsList(
    @Embedded val userEntity: UserEntity,
    @Relation(
        parentColumn = UserDbParams.ID,
        entityColumn = PostDbParams.USER_ID
    )
    val posts: List<PostEntity>
)

@Entity
data class UserWithPost(
    @Embedded val postEntity: PostEntity,
    @Relation(
        parentColumn = PostDbParams.USER_ID,
        entityColumn = UserDbParams.ID
    )
    val userEntity: UserEntity
)