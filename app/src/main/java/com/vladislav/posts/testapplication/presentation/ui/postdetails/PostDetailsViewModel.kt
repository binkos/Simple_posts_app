package com.vladislav.posts.testapplication.presentation.ui.postdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladislav.posts.testapplication.domain.model.PostDetails
import com.vladislav.posts.testapplication.domain.usecase.PostsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PostDetailsViewModel(
    private val postsUseCase: PostsUseCase,
    private val itemId: Long
) : ViewModel() {

    private val _postDetailsStateFlow: MutableStateFlow<PostDetails> =
        MutableStateFlow(PostDetails())
    val postDetailsStateFlow: StateFlow<PostDetails> = _postDetailsStateFlow.asStateFlow()

    private val _isLoadingState = MutableStateFlow(true)
    val isLoadingState: StateFlow<Boolean> = _isLoadingState.asStateFlow()

    init {
        viewModelScope.launch {
            postsUseCase
                .getPostDetailsByIds(itemId)
                .collect {
                    _isLoadingState.emit(false)
                    _postDetailsStateFlow.emit(it)
                }
        }
    }

    fun refreshPostDetails() {
        _isLoadingState.tryEmit(true)
        viewModelScope.launch {
            try {
                postsUseCase.refreshPostDetails(itemId)
            } finally {
                _isLoadingState.emit(false)
            }
        }
    }
}