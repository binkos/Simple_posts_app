package com.vladislav.posts.testapplication.di.module

import android.content.Context
import androidx.room.Room
import com.vladislav.posts.testapplication.data.db.AppDatabase
import com.vladislav.posts.testapplication.data.db.dao.PostDao
import com.vladislav.posts.testapplication.data.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    fun providePostsDao(database: AppDatabase): PostDao = database.getPostDao()

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao = database.getUserDao()

    private companion object {
        private const val DATABASE_NAME = "bitsens_db"
    }
}