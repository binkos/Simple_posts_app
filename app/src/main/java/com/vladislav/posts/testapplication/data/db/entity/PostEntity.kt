package com.vladislav.posts.testapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

object PostDbParams {
    const val TABLE_NAME = "post"
    const val ID = "id"
    const val USER_ID = "user_id"
    const val TITLE = "title"
    const val BODY = "body"
}

@Entity(tableName = PostDbParams.TABLE_NAME)
data class PostEntity(
    @PrimaryKey
    @ColumnInfo(name = PostDbParams.ID)
    val id: Long,
    @ColumnInfo(name = PostDbParams.USER_ID)
    val userId: Long,
    @ColumnInfo(name = PostDbParams.TITLE)
    val title: String,
    @ColumnInfo(name = PostDbParams.BODY)
    val body: String
)