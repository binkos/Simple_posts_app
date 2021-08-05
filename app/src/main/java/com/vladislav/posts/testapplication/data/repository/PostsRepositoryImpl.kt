package com.vladislav.posts.testapplication.data.repository

import com.vladislav.posts.testapplication.data.db.dao.PostDao
import com.vladislav.posts.testapplication.data.db.dao.UserDao
import com.vladislav.posts.testapplication.data.mapper.mapToDomain
import com.vladislav.posts.testapplication.data.mapper.toDb
import com.vladislav.posts.testapplication.data.network.api.Api
import com.vladislav.posts.testapplication.domain.model.Post
import com.vladislav.posts.testapplication.domain.model.PostDetails
import com.vladislav.posts.testapplication.domain.repository.PostsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val postDao: PostDao,
    private val userDao: UserDao
) : PostsRepository {

    override suspend fun getAllPosts(): Flow<Result<List<Post>>> =
        postDao
            .getAllPosts()
            .map { it.map { postEntity -> postEntity.mapToDomain() } }
            .flatMapConcat {
                flow {
                    if (it.isEmpty()) {
                        try {
                            refreshPosts()
                        } catch (ex: Exception) {
                            emit(Result.failure<List<Post>>(ex))
                        }
                    } else {
                        emit(Result.success(it))
                    }
                }
            }

    override suspend fun refreshPostDetails(id: Long) {
        val post = api.getPostById(id)
        val user = api.getUserById(post.userId)

        postDao.insertPost(post.toDb())
        userDao.insertUser(user.toDb())
    }

    override suspend fun getPostDetailsById(id: Long): Flow<PostDetails> =
        postDao
            .getPostById(id)
            .flatMapConcat { post ->
                userDao.getUserById(post.userId)
                    .map { user -> PostDetails(post.title, post.body, user.name, user.avatarUrl) }
            }

    override suspend fun refreshPosts() {
        val posts = api.getAllPosts()
        val setOfUserIdsInPosts = posts.map { post -> post.userId }.toSortedSet()

        setOfUserIdsInPosts
            .map { api.getUserById(it) }
            .asFlow()
            .catch { throw it }
            .map { it.toDb() }
            .toList()
            .also { userDao.insertUsers(it) }

        posts
            .map { post -> post.toDb() }
            .also { postDao.insertPosts(it) }
    }
}