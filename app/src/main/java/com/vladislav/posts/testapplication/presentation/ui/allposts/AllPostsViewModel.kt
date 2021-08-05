package com.vladislav.posts.testapplication.presentation.ui.allposts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladislav.posts.testapplication.domain.model.Post
import com.vladislav.posts.testapplication.domain.usecase.PostsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AllPostsViewModel(private val useCase: PostsUseCase) : ViewModel() {

    init {
        collectPosts()
    }

    private val _allPostsState: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())
    val allPostsState: StateFlow<List<Post>> = _allPostsState.asStateFlow()

    private val _isLoadingState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoadingState: StateFlow<Boolean> = _isLoadingState.asStateFlow()

    private val _isDialogOpenState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDialogOpenState: StateFlow<Boolean> = _isDialogOpenState.asStateFlow()

    fun refreshPosts() {
        _isLoadingState.tryEmit(true)
        _isDialogOpenState.tryEmit(false)
        viewModelScope.launch {
            runCatching { useCase.refreshPosts() }
                .onFailure {
                    _isLoadingState.emit(false)
                    _isDialogOpenState.emit(true)
                }
        }
    }

    fun onDialogDismiss() {
        _isDialogOpenState.tryEmit(false)
    }

    private fun collectPosts() {
        viewModelScope.launch {
            useCase
                .getAllPosts()
                .catch { _isDialogOpenState.emit(true) }
                .collect { result ->
                    result
                        .onSuccess {
                            _isLoadingState.emit(false)
                            _allPostsState.emit(it)
                        }
                        .onFailure {
                            _isLoadingState.emit(false)
                            _isDialogOpenState.emit(true)
                        }
                }
        }
    }
}