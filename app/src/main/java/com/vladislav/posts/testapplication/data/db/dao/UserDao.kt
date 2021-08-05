package com.vladislav.posts.testapplication.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vladislav.posts.testapplication.data.db.entity.UserDbParams
import com.vladislav.posts.testapplication.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query(
        """
        SELECT *
        FROM ${UserDbParams.TABLE_NAME}
        WHERE ${UserDbParams.ID} = :id
        """
    )
    fun getUserById(id: Long): Flow<UserEntity>
}