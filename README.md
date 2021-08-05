# Post - Modern Android Architecture

Posts is a sample project that presents a modern approach to Android app development and load posts.

The project tries to combine popular Android tools and to demonstrate best development practices by utilizing up to date tech-stack like Compose, Kotlin Flow and Dagger.

The sample app presents a modern Android application Architecture that is scalable and maintainable through a MVVM.

## Description

* UI
    * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework
    * [Material design](https://material.io/design)

* Tech/Tools
    * [Kotlin](https://kotlinlang.org/) 100% coverage
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
    * [Dagger 2](https://dagger.dev/dev-guide/) for dependency injection
    * [Jetpack](https://developer.android.com/jetpack)
        * [Compose](https://developer.android.com/jetpack/compose)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) for navigation between composables
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
    * [Retrofit](https://square.github.io/retrofit/) for networking
    * [Coil](https://github.com/coil-kt/coil) for image loading

* Modern Architecture
    * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs
    * MVVM blend with MVI
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
