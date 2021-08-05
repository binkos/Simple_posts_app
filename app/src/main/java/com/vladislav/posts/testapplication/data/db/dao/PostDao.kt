package com.vladislav.posts.testapplication.data.db.dao

import androidx.room.*
import com.vladislav.posts.testapplication.data.db.entity.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("SELECT * FROM ${PostDbParams.TABLE_NAME}")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Query(
        """
        SELECT *
        FROM ${PostDbParams.TABLE_NAME}
        WHERE ${PostDbParams.ID} = :id
        """
    )
    fun getPostById(id: Long): Flow<PostEntity>
}