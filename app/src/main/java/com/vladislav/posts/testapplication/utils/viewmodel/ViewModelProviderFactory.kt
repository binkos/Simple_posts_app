package com.vladislav.posts.testapplication.utils.viewmodel

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

@MainThread
@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.fragmentViewModelProvider(
    noinline viewModelInitializer: () -> VM
): Lazy<VM> = ViewModelLazy(
    viewModelClass = VM::class,
    storeProducer = { viewModelStore },
    factoryProducer = { ViewModelProviderFactory(viewModelInitializer) }
)

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory<VM : ViewModel>(
    private val viewModelInitializer: () -> VM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModelInitializer.invoke() as T
}