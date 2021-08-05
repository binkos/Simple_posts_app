package com.vladislav.posts.testapplication.di.component

import android.app.Application
import android.content.Context
import com.vladislav.posts.testapplication.di.module.*
import com.vladislav.posts.testapplication.domain.usecase.PostsUseCase
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        DispatchersProviderModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(application: Application)

    val postsUseCase: PostsUseCase

    @Component.Factory
    interface Builder {
        fun build(@BindsInstance context: Context): AppComponent
    }

    companion object {
        operator fun invoke(context: Context): AppComponent =
            DaggerAppComponent.factory().build(context)
    }
}