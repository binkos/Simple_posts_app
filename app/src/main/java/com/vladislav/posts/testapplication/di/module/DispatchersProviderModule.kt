package com.vladislav.posts.testapplication.di.module

import com.vladislav.posts.testapplication.core.dispatchers.DispatchersProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class DispatchersProviderModule {

    @Provides
    fun providerDispatchersProvider():DispatchersProvider =
        object : DispatchersProvider {
            override val default: CoroutineDispatcher
                get() = Dispatchers.Default
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
            override val main: CoroutineDispatcher
                get() = Dispatchers.Main
            override val unconfined: CoroutineDispatcher
                get() = Dispatchers.Unconfined
        }
}