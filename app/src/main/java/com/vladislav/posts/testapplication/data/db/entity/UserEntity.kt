package com.vladislav.posts.testapplication.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

object UserDbParams {
    const val TABLE_NAME = "user"
    const val ID = "id"
    const val NAME = "name"
    const val AVATAR_URL = "url"
}

@Entity(tableName = UserDbParams.TABLE_NAME)
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = UserDbParams.ID)
    val id: Long,
    @ColumnInfo(name = UserDbParams.NAME)
    val name: String,
    @ColumnInfo(name = UserDbParams.AVATAR_URL)
    val avatarUrl: String
)