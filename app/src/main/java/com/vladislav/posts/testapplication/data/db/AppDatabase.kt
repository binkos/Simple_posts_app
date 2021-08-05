package com.vladislav.posts.testapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vladislav.posts.testapplication.data.db.dao.PostDao
import com.vladislav.posts.testapplication.data.db.dao.UserDao
import com.vladislav.posts.testapplication.data.db.entity.PostEntity
import com.vladislav.posts.testapplication.data.db.entity.UserEntity

@Database(entities = [PostEntity::class, UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    abstract fun getUserDao(): UserDao
}